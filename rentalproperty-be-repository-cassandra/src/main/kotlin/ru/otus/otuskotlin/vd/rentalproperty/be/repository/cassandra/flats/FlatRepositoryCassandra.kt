package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.flats

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.metadata.schema.ClusteringOrder
import com.datastax.oss.driver.api.core.type.DataTypes
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoIndexException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoModifyException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoNotFoundException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoWrongIdException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IFlatRepository
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common.RepositoryCassandra
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common.dto.DirectoryCassandraDto
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common.dto.MediaFileCassandraDto
import java.time.Duration
import java.util.*

class FlatRepositoryCassandra(
  override val keyspaceName: String,
  override val hosts: String = "",
  override val port: Int = 9042,
  override val user: String = "cassandra",
  override val pass: String = "cassandra",
  override val replicationFactor: Int = 1,
  override val testing: Boolean = false,
  private val timeout: Duration = Duration.ofSeconds(30),
  initObjects: Collection<FlatModel> = emptyList(),
) : IFlatRepository, RepositoryCassandra(keyspaceName, hosts, port, user, pass, replicationFactor) {

  private val mapper by lazy {
    FlatCassandraMapperBuilder(session).build()
  }

  /**
   *  DAO для операций по id
   */
  private val daoById by lazy {
    mapper.flatByIdDao(keyspaceName, FlatByIdCassandraDto.TABLE_NAME).apply {
      runBlocking {
        initObjects.map { model ->
          withTimeout(timeout.toMillis()) {
            createAsync(FlatByIdCassandraDto.of(model)).await()
          }
        }
      }
    }
  }

  /**
   *  DAO для операций с названиями
   */
  private val daoByDescription by lazy {
    mapper.flatByDescriptionDao(keyspaceName, FlatByDescriptionCassandraDto.TABLE_NAME).apply {
      runBlocking {
        initObjects.map { model ->
          withTimeout(timeout.toMillis()) {
            createAsync(FlatByDescriptionCassandraDto.of(model)).await()
          }
        }
      }
    }
  }

  override suspend fun read(context: BeContext): FlatModel {
    val id = context.requestFlatId
    if (id == FlatIdModel.NONE) throw RepoWrongIdException(id.id)
    return withTimeout(timeout.toMillis()) {
      val model = daoById.readAsync(id.id).await()?.toModel() ?: throw RepoNotFoundException(id.id)
      context.responseFlat = model
      model
    }
  }

  /**
   * Запись происходит во все таблицы
   */
  override suspend fun create(context: BeContext): FlatModel {
    val id = UUID.randomUUID().toString()
    val dtoById = FlatByIdCassandraDto.of(context.requestFlat, id)
    val dtoByTitle = FlatByDescriptionCassandraDto.of(context.requestFlat, id)
    return withTimeout(timeout.toMillis()) {
      daoById.createAsync(dtoById).await()
      daoByDescription.createAsync(dtoByTitle).await()
      val model = daoById.readAsync(id).await()?.toModel() ?: throw RepoNotFoundException(id)
      context.responseFlat = model
      model
    }
  }

  /**
   *  Использование Optimistic Lock для примера, в данном случае update аналогичен create
   */
  override suspend fun update(context: BeContext): FlatModel {
    val id = context.requestFlat.id
    if (id == FlatIdModel.NONE) throw RepoWrongIdException(id.id)
    return withTimeout(timeout.toMillis()) {
      val lockKey = daoById.readAsync(id.id).await()?.lockVersion ?: throw RepoNotFoundException(id.id)
      val dtoById = FlatByIdCassandraDto.of(context.requestFlat)
      val dtoByDescription = FlatByDescriptionCassandraDto.of(context.requestFlat)
      val isUpdated = daoById.updateAsync(dtoById, lockKey).await()
      if (!isUpdated) throw RepoModifyException(id.id)
      if (dtoByDescription.description != null) daoByDescription.createAsync(dtoByDescription).await()
      val model = daoById.readAsync(id.id).await()?.toModel() ?: throw RepoNotFoundException(id.id)
      context.responseFlat = model
      model
    }
  }

  /**
   * Удаление записи из всех таблиц
   */
  override suspend fun delete(context: BeContext): FlatModel {
    val id = context.requestFlatId
    if (id == FlatIdModel.NONE) throw RepoWrongIdException(id.id)
    return withTimeout(timeout.toMillis()) {
      val model = daoById.readAsync(id.id).await()?.toModel() ?: throw RepoNotFoundException(id.id)
      daoById.deleteAsync(id.id).await()
      daoByDescription.deleteAsync(FlatByDescriptionCassandraDto.of(model)).await()
      context.responseFlat = model
      model
    }
  }

  override suspend fun list(context: BeContext): Collection<FlatModel> {
    val filter = context.flatFilter
    var lastIndex = filter.offset + filter.count
    if (filter.text.length < 3) throw RepoIndexException(filter.text)
    return withTimeout(timeout.toMillis()) {
      val records = daoByDescription.filterByDescriptionAsync("%${filter.text}%").await().toList()
        .sortedByDescending { it.timestamp }
      val recordsCount = records.count()
      if (recordsCount < lastIndex) lastIndex = recordsCount
      val list = flow {
        for (pos in filter.offset until lastIndex) {
          records[pos].id?.let { id ->
            emit(daoById.readAsync(id).await()?.toModel() ?: throw RepoNotFoundException(id))
          }
        }
      }.toList()
      context.responseFlats = list.toMutableList()
      context.pageCount = list.count().takeIf { it != 0 }
        ?.let { (recordsCount.toDouble() / it + 0.5).toInt() }
        ?: Int.MIN_VALUE
      list
    }
  }

  override fun CqlSession.createTables() {
    execute(
      SchemaBuilder.createTable(FlatByDescriptionCassandraDto.TABLE_NAME)
        .ifNotExists()
        .withPartitionKey(FlatByDescriptionCassandraDto.DESCRIPTION, DataTypes.TEXT)
        .withClusteringColumn(FlatByDescriptionCassandraDto.TIMESTAMP, DataTypes.TIMESTAMP)
        .withClusteringColumn(FlatByDescriptionCassandraDto.ID, DataTypes.TEXT)
        .withClusteringColumn(FlatByDescriptionCassandraDto.DESCRIPTION_INDEX, DataTypes.TEXT)
        .withClusteringOrder(FlatByDescriptionCassandraDto.TIMESTAMP, ClusteringOrder.DESC)
        .withClusteringOrder(FlatByDescriptionCassandraDto.ID, ClusteringOrder.ASC)
        .withClusteringOrder(FlatByDescriptionCassandraDto.DESCRIPTION_INDEX, ClusteringOrder.ASC)
        .build()
    )
    execute(
      SchemaBuilder.createTable(FlatByIdCassandraDto.TABLE_NAME)
        .ifNotExists()
        .withPartitionKey(FlatByIdCassandraDto.ID, DataTypes.TEXT)
        .withColumn(FlatByIdCassandraDto.HOUSE_ID, DataTypes.TEXT)
        .withColumn(FlatByIdCassandraDto.NUMBER, DataTypes.TEXT)
        .withColumn(FlatByIdCassandraDto.AREA, DataTypes.DOUBLE)
        .withColumn(FlatByIdCassandraDto.AREA_LIVING, DataTypes.DOUBLE)
        .withColumn(FlatByIdCassandraDto.AREA_KITCHEN, DataTypes.DOUBLE)
        .withColumn(FlatByIdCassandraDto.ROOMS, DataTypes.INT)
        .withColumn(FlatByIdCassandraDto.FLOOR, DataTypes.INT)
        .withColumn(FlatByIdCassandraDto.CEILING_HEIGHT, DataTypes.DOUBLE)
        .withColumn(FlatByIdCassandraDto.BEDROOMS, DataTypes.INT)
        .withColumn(FlatByIdCassandraDto.BEDS, DataTypes.INT)
        .withColumn(FlatByIdCassandraDto.BATHROOMS, DataTypes.INT)
        .withColumn(
          FlatByIdCassandraDto.BATHROOM_TYPE,
          SchemaBuilder.udt(DirectoryCassandraDto.TYPE_NAME, true)
        )
        .withColumn(FlatByIdCassandraDto.BALCONY, DataTypes.INT)
        .withColumn(FlatByIdCassandraDto.LOGGIA, DataTypes.INT)
        .withColumn(
          FlatByIdCassandraDto.REPAIR_TYPE,
          SchemaBuilder.udt(DirectoryCassandraDto.TYPE_NAME, true)
        )
        .withColumn(
          FlatByIdCassandraDto.VIEW_FROM_WINDOW,
          SchemaBuilder.udt(DirectoryCassandraDto.TYPE_NAME, true)
        )
        .withColumn(
          FlatByIdCassandraDto.CONVENIENCES,
          DataTypes.setOf(SchemaBuilder.udt(DirectoryCassandraDto.TYPE_NAME, true))
        )
        .withColumn(
          FlatByIdCassandraDto.APPLIANCES,
          DataTypes.setOf(SchemaBuilder.udt(DirectoryCassandraDto.TYPE_NAME, true))
        )
        .withColumn(FlatByIdCassandraDto.RESIDENTS, DataTypes.INT)
        .withColumn(FlatByIdCassandraDto.NO_SMOKING, DataTypes.BOOLEAN)
        .withColumn(FlatByIdCassandraDto.NO_ANIMALS, DataTypes.BOOLEAN)
        .withColumn(FlatByIdCassandraDto.NO_CHILDREN, DataTypes.BOOLEAN)
        .withColumn(FlatByIdCassandraDto.NO_PARTIES, DataTypes.BOOLEAN)
        .withColumn(FlatByIdCassandraDto.DESCRIPTION, DataTypes.TEXT)
        .withColumn(
          FlatByIdCassandraDto.PHOTOS,
          DataTypes.setOf(SchemaBuilder.udt(MediaFileCassandraDto.TYPE_NAME, true))
        )
        .withColumn(FlatByIdCassandraDto.LOCK_VERSION, DataTypes.TEXT)
        .build()
    )
  }

  override fun CqlSession.createIndexes() {
    execute(
      SchemaBuilder.createIndex()
        .ifNotExists()
        .usingSASI()
        .onTable(FlatByDescriptionCassandraDto.TABLE_NAME)
        .andColumn(FlatByDescriptionCassandraDto.DESCRIPTION_INDEX)
        .withSASIOptions(mapOf("mode" to "CONTAINS", "tokenization_locale" to "en"))
        .build()
    )
  }

  override fun init() = apply {
    val dao1 = daoById
    val dao2 = daoByDescription
  }
}
