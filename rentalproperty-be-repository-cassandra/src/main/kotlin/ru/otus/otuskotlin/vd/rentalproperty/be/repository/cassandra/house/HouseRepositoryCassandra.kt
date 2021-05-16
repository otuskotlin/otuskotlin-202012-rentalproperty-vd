package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.house

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
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IHouseRepository
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common.RepositoryCassandra
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common.dto.DirectoryCassandraDto
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common.dto.MediaFileCassandraDto
import java.time.Duration
import java.util.*

class HouseRepositoryCassandra(
  override val keyspaceName: String,
  override val hosts: String = "",
  override val port: Int = 9042,
  override val user: String = "cassandra",
  override val pass: String = "cassandra",
  override val replicationFactor: Int = 1,
  override val testing: Boolean = false,
  private val timeout: Duration = Duration.ofSeconds(30),
  initObjects: Collection<HouseModel> = emptyList(),
) : IHouseRepository, RepositoryCassandra(keyspaceName, hosts, port, user, pass, replicationFactor) {

  private val mapper by lazy {
    HouseCassandraMapperBuilder(session).build()
  }

  /**
   *  DAO для операций по id
   */
  private val daoById by lazy {
    mapper.houseByIdDao(keyspaceName, HouseByIdCassandraDto.TABLE_NAME).apply {
      runBlocking {
        initObjects.map { model ->
          withTimeout(timeout.toMillis()) {
            createAsync(HouseByIdCassandraDto.of(model)).await()
          }
        }
      }
    }
  }

  /**
   *  DAO для операций с названиями
   */
  private val daoByAddress by lazy {
    mapper.houseByAddressDao(keyspaceName, HouseByAddressCassandraDto.TABLE_NAME).apply {
      runBlocking {
        initObjects.map { model ->
          withTimeout(timeout.toMillis()) {
            createAsync(HouseByAddressCassandraDto.of(model)).await()
          }
        }
      }
    }
  }

  override suspend fun read(context: BeContext): HouseModel {
    val id = context.requestHouseId
    if (id == HouseIdModel.NONE) throw RepoWrongIdException(id.id)
    return withTimeout(timeout.toMillis()) {
      val model = daoById.readAsync(id.id).await()?.toModel() ?: throw RepoNotFoundException(id.id)
      context.responseHouse = model
      model
    }
  }

  /**
   * Запись происходит во все таблицы
   */
  override suspend fun create(context: BeContext): HouseModel {
    val id = UUID.randomUUID().toString()
    val dtoById = HouseByIdCassandraDto.of(context.requestHouse, id)
    val dtoByTitle = HouseByAddressCassandraDto.of(context.requestHouse, id)
    return withTimeout(timeout.toMillis()) {
      daoById.createAsync(dtoById).await()
      daoByAddress.createAsync(dtoByTitle).await()
      val model = daoById.readAsync(id).await()?.toModel() ?: throw RepoNotFoundException(id)
      context.responseHouse = model
      model
    }
  }

  /**
   *  Использование Optimistic Lock для примера, в данном случае update аналогичен create
   */
  override suspend fun update(context: BeContext): HouseModel {
    val id = context.requestHouse.id
    if (id == HouseIdModel.NONE) throw RepoWrongIdException(id.id)
    return withTimeout(timeout.toMillis()) {
      val lockKey = daoById.readAsync(id.id).await()?.lockVersion ?: throw RepoNotFoundException(id.id)
      val dtoById = HouseByIdCassandraDto.of(context.requestHouse)
      val dtoByAddress = HouseByAddressCassandraDto.of(context.requestHouse)
      val isUpdated = daoById.updateAsync(dtoById, lockKey).await()
      if (!isUpdated) throw RepoModifyException(id.id)
      if (dtoByAddress.address != null) daoByAddress.createAsync(dtoByAddress).await()
      val model = daoById.readAsync(id.id).await()?.toModel() ?: throw RepoNotFoundException(id.id)
      context.responseHouse = model
      model
    }
  }

  /**
   * Удаление записи из всех таблиц
   */
  override suspend fun delete(context: BeContext): HouseModel {
    val id = context.requestHouseId
    if (id == HouseIdModel.NONE) throw RepoWrongIdException(id.id)
    return withTimeout(timeout.toMillis()) {
      val model = daoById.readAsync(id.id).await()?.toModel() ?: throw RepoNotFoundException(id.id)
      daoById.deleteAsync(id.id).await()
      daoByAddress.deleteAsync(HouseByAddressCassandraDto.of(model)).await()
      context.responseHouse = model
      model
    }
  }

  override suspend fun list(context: BeContext): Collection<HouseModel> {
    val filter = context.houseFilter
    var lastIndex = filter.offset + filter.count
    if (filter.text.length < 3) throw RepoIndexException(filter.text)
    return withTimeout(timeout.toMillis()) {
      val records = daoByAddress.filterByAddressAsync("%${filter.text}%").await().toList()
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
      context.responseHouses = list.toMutableList()
      context.pageCount = list.count().takeIf { it != 0 }
        ?.let { (recordsCount.toDouble() / it + 0.5).toInt() }
        ?: Int.MIN_VALUE
      list
    }
  }

  override fun CqlSession.createTables() {
    execute(
      SchemaBuilder.createTable(HouseByAddressCassandraDto.TABLE_NAME)
        .ifNotExists()
        .withPartitionKey(HouseByAddressCassandraDto.ADDRESS, DataTypes.TEXT)
        .withClusteringColumn(HouseByAddressCassandraDto.TIMESTAMP, DataTypes.TIMESTAMP)
        .withClusteringColumn(HouseByAddressCassandraDto.ID, DataTypes.TEXT)
        .withClusteringColumn(HouseByAddressCassandraDto.ADDRESS_INDEX, DataTypes.TEXT)
        .withClusteringOrder(HouseByAddressCassandraDto.TIMESTAMP, ClusteringOrder.DESC)
        .withClusteringOrder(HouseByAddressCassandraDto.ID, ClusteringOrder.ASC)
        .withClusteringOrder(HouseByAddressCassandraDto.ADDRESS_INDEX, ClusteringOrder.ASC)
        .build()
    )
    execute(
      SchemaBuilder.createTable(HouseByIdCassandraDto.TABLE_NAME)
        .ifNotExists()
        .withPartitionKey(HouseByIdCassandraDto.ID, DataTypes.TEXT)
        .withColumn(HouseByIdCassandraDto.ADDRESS, DataTypes.TEXT)
        .withColumn(HouseByIdCassandraDto.AREA, DataTypes.DOUBLE)
        .withColumn(
          HouseByIdCassandraDto.MATERIAL,
          SchemaBuilder.udt(DirectoryCassandraDto.TYPE_NAME, true)
        )
        .withColumn(
          HouseByIdCassandraDto.TYPE,
          SchemaBuilder.udt(DirectoryCassandraDto.TYPE_NAME, true)
        )
        .withColumn(HouseByIdCassandraDto.SERIES, DataTypes.TEXT)
        .withColumn(HouseByIdCassandraDto.FLOORS, DataTypes.INT)
        .withColumn(HouseByIdCassandraDto.AREA_PLOT, DataTypes.DOUBLE)
        .withColumn(
          HouseByIdCassandraDto.PLOT_STATUS,
          SchemaBuilder.udt(DirectoryCassandraDto.TYPE_NAME, true)
        )
        .withColumn(
          HouseByIdCassandraDto.INFRASTRUCTURE,
          DataTypes.setOf(SchemaBuilder.udt(DirectoryCassandraDto.TYPE_NAME, true))
        )
        .withColumn(HouseByIdCassandraDto.YEAR_CONSTRUCTION, DataTypes.INT)
        .withColumn(HouseByIdCassandraDto.GARBAGE_CHUTE, DataTypes.BOOLEAN)
        .withColumn(HouseByIdCassandraDto.UNIT_ON_FLOOR, DataTypes.INT)
        .withColumn(HouseByIdCassandraDto.PASSENGER_ELEVATOR, DataTypes.INT)
        .withColumn(HouseByIdCassandraDto.SERVICE_ELEVATOR, DataTypes.INT)
        .withColumn(HouseByIdCassandraDto.METRO, DataTypes.TEXT)
        .withColumn(HouseByIdCassandraDto.TIME_TO_METRO, DataTypes.INT)
        .withColumn(HouseByIdCassandraDto.DISTANCE_TO_METRO, DataTypes.INT)
        .withColumn(
          HouseByIdCassandraDto.PHOTOS,
          DataTypes.setOf(SchemaBuilder.udt(MediaFileCassandraDto.TYPE_NAME, true))
        )
        .withColumn(HouseByIdCassandraDto.LOCK_VERSION, DataTypes.TEXT)
        .build()
    )
  }

  override fun CqlSession.createIndexes() {
    execute(
      SchemaBuilder.createIndex()
        .ifNotExists()
        .usingSASI()
        .onTable(HouseByAddressCassandraDto.TABLE_NAME)
        .andColumn(HouseByAddressCassandraDto.ADDRESS_INDEX)
        .withSASIOptions(mapOf("mode" to "CONTAINS", "tokenization_locale" to "en"))
        .build()
    )
  }

  override fun init() = apply {
    val dao1 = daoById
    val dao2 = daoByAddress
  }
}
