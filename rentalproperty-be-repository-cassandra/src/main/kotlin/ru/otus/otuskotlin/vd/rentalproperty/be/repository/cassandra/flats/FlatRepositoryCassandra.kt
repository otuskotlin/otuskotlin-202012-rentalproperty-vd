package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.flats

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.metadata.schema.ClusteringOrder
import com.datastax.oss.driver.api.core.type.DataTypes
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoIndexException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoNotFoundException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoWrongIdException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IFlatRepository
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common.RepositoryCassandra
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
        mapper.flatByIdDao(keyspaceName, FlatByIdCassandraDto.DEMANDS_TABLE_NAME).apply {
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
    private val daoByTitle by lazy {
        mapper.flatByTitleDao(keyspaceName, FlatByTitleCassandraDto.DEMANDS_TITLE_TABLE_NAME).apply {
            runBlocking {
                initObjects.map { model ->
                    withTimeout(timeout.toMillis()) {
                        createAsync(FlatByTitleCassandraDto.of(model)).await()
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
        val dtoByTitle = FlatByTitleCassandraDto.of(context.requestFlat, id)
        return withTimeout(timeout.toMillis()) {
            daoById.createAsync(dtoById).await()
            daoByTitle.createAsync(dtoByTitle).await()
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
            val dtoByTitle = FlatByTitleCassandraDto.of(context.requestFlat)
            val isUpdated = daoById.updateAsync(dtoById, lockKey).await()
            if (!isUpdated) throw RepoModifyException(id.id)
            daoByTitle.createAsync(dtoByTitle).await()
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
            daoByTitle.deleteAsync(FlatByTitleCassandraDto.of(model)).await()
            context.responseFlat = model
            model
        }
    }

    override suspend fun list(context: BeContext): Collection<FlatModel> {
        val filter = context.flatFilter
        var lastIndex = filter.offset + filter.count
        if (filter.text.length < 3) throw RepoIndexException(filter.text)
        return withTimeout(timeout.toMillis()) {
            val records = daoByTitle.filterByTitleAsync("%${filter.text}%").await().toList()
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

    override suspend fun offers(context: BeContext): Collection<FlatModel> {
        val filter = context.requestProposal.title
        if (filter.length < 3) throw RepoIndexException(filter)
        return withTimeout(timeout.toMillis()) {
            val records = daoByTitle.filterByTitleAsync("%${filter}%").await().toList()
            val list = flow {
                records.forEach { dto ->
                    dto.id?.let {
                        emit(
                            daoById.readAsync(it).await()?.toModel()
                                ?: throw RepoNotFoundException(it)
                        )
                    }
                }
            }.toList()
            context.responseFlats = list.toMutableList()
            list
        }
    }

    override fun CqlSession.createTables() {
        execute(
            SchemaBuilder.createTable(FlatByTitleCassandraDto.DEMANDS_TITLE_TABLE_NAME)
                .ifNotExists()
                .withPartitionKey(FlatByTitleCassandraDto.TITLE, DataTypes.TEXT)
                .withClusteringColumn(FlatByTitleCassandraDto.TIMESTAMP, DataTypes.TIMESTAMP)
                .withClusteringColumn(FlatByTitleCassandraDto.ID, DataTypes.TEXT)
                .withClusteringColumn(FlatByTitleCassandraDto.TITLE_INDEX, DataTypes.TEXT)
                .withClusteringOrder(FlatByTitleCassandraDto.TIMESTAMP, ClusteringOrder.DESC)
                .withClusteringOrder(FlatByTitleCassandraDto.ID, ClusteringOrder.ASC)
                .withClusteringOrder(FlatByTitleCassandraDto.TITLE_INDEX, ClusteringOrder.ASC)
                .build()
        )
        execute(
            SchemaBuilder.createTable(FlatByIdCassandraDto.DEMANDS_TABLE_NAME)
                .ifNotExists()
                .withPartitionKey(FlatByIdCassandraDto.ID, DataTypes.TEXT)
                .withColumn(FlatByIdCassandraDto.AVATAR, DataTypes.TEXT)
                .withColumn(FlatByIdCassandraDto.DESCRIPTION, DataTypes.TEXT)
                .withColumn(FlatByIdCassandraDto.TITLE, DataTypes.TEXT)
                .withColumn(FlatByIdCassandraDto.TAG_ID_LIST, DataTypes.setOf(DataTypes.TEXT))
                .withColumn(
                    FlatByIdCassandraDto.TECH_DETS,
                    DataTypes.setOf(SchemaBuilder.udt(TechDetCassandraDto.TYPE_NAME, true))
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
                .onTable(FlatByTitleCassandraDto.DEMANDS_TITLE_TABLE_NAME)
                .andColumn(FlatByTitleCassandraDto.TITLE_INDEX)
                .withSASIOptions(mapOf("mode" to "CONTAINS", "tokenization_locale" to "en"))
                .build()
        )
    }

    override fun init() = apply {
        val dao1 = daoById
        val dao2 = daoByTitle
    }

}
