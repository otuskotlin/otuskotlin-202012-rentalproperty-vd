package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.directory

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
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IDirectoryRepository
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryItemIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.IDirectoryItemModel
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common.RepositoryCassandra
import java.time.Duration
import java.util.*

class DirectoryRepositoryCassandra(
  override val keyspaceName: String,
  override val hosts: String = "",
  override val port: Int = 9042,
  override val user: String = "cassandra",
  override val pass: String = "cassandra",
  override val replicationFactor: Int = 1,
  override val testing: Boolean = false,
  private val timeout: Duration = Duration.ofSeconds(30),
  initObjects: Collection<IDirectoryItemModel> = emptyList(),
) : IDirectoryRepository, RepositoryCassandra(keyspaceName, hosts, port, user, pass, replicationFactor) {

  private val mapper by lazy {
    DirectoryCassandraMapperBuilder(session).build()
  }

  /**
   *  DAO для операций по id
   */
  private val daoById by lazy {
    mapper.directoryByIdDao(keyspaceName, DirectoryByIdCassandraDto.TABLE_NAME).apply {
      runBlocking {
        initObjects.map { model ->
          withTimeout(timeout.toMillis()) {
            createAsync(DirectoryByIdCassandraDto.of(model)).await()
          }
        }
      }
    }
  }

  /**
   *  DAO для операций с названиями
   */
  private val daoByType by lazy {
    mapper.directoryByTypeDao(keyspaceName, DirectoryByTypeCassandraDto.TABLE_NAME).apply {
      runBlocking {
        initObjects.map { model ->
          withTimeout(timeout.toMillis()) {
            createAsync(DirectoryByTypeCassandraDto.of(model)).await()
          }
        }
      }
    }
  }

  override suspend fun read(context: BeContext): IDirectoryItemModel {
    val id = context.requestDirectoryItemId
    if (id == DirectoryItemIdModel.NONE) throw RepoWrongIdException(id.id)
    return withTimeout(timeout.toMillis()) {
      val model = daoById.readAsync(id.id).await()?.toModel() ?: throw RepoNotFoundException(id.id)
      context.responseDirectoryItem = model
      model
    }
  }

  /**
   * Запись происходит во все таблицы
   */
  override suspend fun create(context: BeContext): IDirectoryItemModel {
    val id = UUID.randomUUID().toString()
    val dtoById = DirectoryByIdCassandraDto.of(context.requestDirectoryItem, id)
    return withTimeout(timeout.toMillis()) {
      daoById.createAsync(dtoById).await()
      val model = daoById.readAsync(id).await()?.toModel() ?: throw RepoNotFoundException(id)
      context.responseDirectoryItem = model
      model
    }
  }

  /**
   *  Использование Optimistic Lock для примера, в данном случае update аналогичен create
   */
  override suspend fun update(context: BeContext): IDirectoryItemModel {
    val id = context.requestDirectoryItem.id
    if (id == DirectoryItemIdModel.NONE) throw RepoWrongIdException(id.id)
    return withTimeout(timeout.toMillis()) {
      val lockKey = daoById.readAsync(id.id).await()?.lockVersion ?: throw RepoNotFoundException(id.id)
      val dtoById = DirectoryByIdCassandraDto.of(context.requestDirectoryItem)
      val dtoByType = DirectoryByTypeCassandraDto.of(context.requestDirectoryItem)
      val isUpdated = daoById.updateAsync(dtoById, lockKey).await()
      if (!isUpdated) throw RepoModifyException(id.id)
      if (dtoByType.type != null) daoByType.createAsync(dtoByType).await()
      val model = daoById.readAsync(id.id).await()?.toModel() ?: throw RepoNotFoundException(id.id)
      context.responseDirectoryItem = model
      model
    }
  }

  /**
   * Удаление записи из всех таблиц
   */
  override suspend fun delete(context: BeContext): IDirectoryItemModel {
    val id = context.requestDirectoryItemId
    if (id == DirectoryItemIdModel.NONE) throw RepoWrongIdException(id.id)
    return withTimeout(timeout.toMillis()) {
      val model = daoById.readAsync(id.id).await()?.toModel() ?: throw RepoNotFoundException(id.id)
      daoById.deleteAsync(id.id).await()
      daoByType.deleteAsync(DirectoryByTypeCassandraDto.of(model)).await()
      context.responseDirectoryItem = model
      model
    }
  }

  override suspend fun list(context: BeContext): Collection<IDirectoryItemModel> {
    val filter = context.directoryFilter
    var lastIndex = filter.offset + filter.count
    if (filter.text.length < 3) throw RepoIndexException(filter.text)
    return withTimeout(timeout.toMillis()) {
      val records = daoByType.filterByTypeAsync("%${filter.text}%").await().toList()
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
      context.responseDirectoryItems = list.toMutableList()
      context.pageCount = list.count().takeIf { it != 0 }
        ?.let { (recordsCount.toDouble() / it + 0.5).toInt() }
        ?: Int.MIN_VALUE
      list
    }
  }

  override fun CqlSession.createTables() {
    execute(
      SchemaBuilder.createTable(DirectoryByTypeCassandraDto.TABLE_NAME)
        .ifNotExists()
        .withPartitionKey(DirectoryByTypeCassandraDto.TYPE, DataTypes.TEXT)
        .withClusteringColumn(DirectoryByTypeCassandraDto.TIMESTAMP, DataTypes.TIMESTAMP)
        .withClusteringColumn(DirectoryByTypeCassandraDto.ID, DataTypes.TEXT)
        .withClusteringColumn(DirectoryByTypeCassandraDto.TYPE_INDEX, DataTypes.TEXT)
        .withClusteringOrder(DirectoryByTypeCassandraDto.TIMESTAMP, ClusteringOrder.DESC)
        .withClusteringOrder(DirectoryByTypeCassandraDto.ID, ClusteringOrder.ASC)
        .withClusteringOrder(DirectoryByTypeCassandraDto.TYPE_INDEX, ClusteringOrder.ASC)
        .build()
    )
    execute(
      SchemaBuilder.createTable(DirectoryByIdCassandraDto.TABLE_NAME)
        .ifNotExists()
        .withPartitionKey(DirectoryByIdCassandraDto.ID, DataTypes.TEXT)
        .withColumn(DirectoryByIdCassandraDto.NAME, DataTypes.TEXT)
        .withColumn(DirectoryByIdCassandraDto.TYPE, DataTypes.TEXT)
        .withColumn(DirectoryByIdCassandraDto.LOCK_VERSION, DataTypes.TEXT)
        .build()
    )
  }

  override fun CqlSession.createIndexes() {
    execute(
      SchemaBuilder.createIndex()
        .ifNotExists()
        .usingSASI()
        .onTable(DirectoryByTypeCassandraDto.TABLE_NAME)
        .andColumn(DirectoryByTypeCassandraDto.TYPE_INDEX)
        .withSASIOptions(mapOf("mode" to "CONTAINS", "tokenization_locale" to "en"))
        .build()
    )
  }

  override fun init() = apply {
    val dao1 = daoById
    val dao2 = daoByType
  }
}
