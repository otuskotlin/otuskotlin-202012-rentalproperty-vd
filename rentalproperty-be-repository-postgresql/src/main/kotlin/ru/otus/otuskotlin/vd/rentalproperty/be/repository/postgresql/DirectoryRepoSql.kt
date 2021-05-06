package ru.otus.otuskotlin.vd.rentalproperty.be.repository.postgresql

import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoWrongIdException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IDirectoryRepository
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryItemIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.IDirectoryItemModel
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.postgresql.schema.DirectoriesTable
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.postgresql.schema.DirectoryDto
import java.sql.Connection

class DirectoryRepoSql(
  url: String = "jdbc:postgresql://localhost:5432/rentalproperty",
  driver: String = "org.postgresql.Driver",
  user: String = "postgres",
  password: String = "postgres",
  val printLogs: Boolean = false,
  initObjects: Collection<IDirectoryItemModel> = emptyList()
) : IDirectoryRepository {

  private val db by lazy {
    val _db = Database.connect(
      url = url,
      driver = driver,
      user = user,
      password = password
    )
    transaction {
      SchemaUtils.create(DirectoriesTable)
    }
    _db
  }

  init {
    runBlocking {
      initObjects.forEach {
        createWithId(BeContext(requestDirectoryItem = it), true)
      }
    }
  }

  override suspend fun list(context: BeContext): Collection<IDirectoryItemModel> {
    val filter = context.directoryFilter
    return transaction(
      transactionIsolation = Connection.TRANSACTION_SERIALIZABLE,
      repetitionAttempts = 3,
      db = db
    ) {
      if (printLogs) addLogger(StdOutSqlLogger)
      val found =
        if (filter.text.isNotBlank()) {
          DirectoryDto.find {
            (DirectoriesTable.name like "%${filter.text}%")
          }
        } else {
          DirectoryDto.all()
        }
      context.pageCount = found.count().toInt()
      found
        .limit(filter.count.takeIf { it > 0 } ?: 20,
          filter.offset.toLong().takeIf { it > 0 } ?: 0)
        .toList()
      context.responseDirectoryItems = found.map { it.toModel() }.toMutableList()
      context.responseDirectoryItems
    }
  }

  override suspend fun create(context: BeContext): IDirectoryItemModel = createWithId(context)

  private suspend fun createWithId(context: BeContext, setId: Boolean = false): IDirectoryItemModel {
    val model = context.requestDirectoryItem
    return transaction(db) {
      if (printLogs) addLogger(StdOutSqlLogger)
      val flatNew = DirectoryDto.new(if (setId) model.id.asUUID() else null) { of(model) }
      context.responseDirectoryItem = flatNew.toModel()
      context.responseDirectoryItem
    }
  }

  override suspend fun read(context: BeContext): IDirectoryItemModel {
    val id = context.requestDirectoryItemId
    if (id == DirectoryItemIdModel.NONE) throw RepoWrongIdException(id.id)
    return transaction {
      if (printLogs) addLogger(StdOutSqlLogger)
      context.responseDirectoryItem = DirectoryDto[id.asUUID()].toModel()
      context.responseDirectoryItem
    }
  }

  override suspend fun update(context: BeContext): IDirectoryItemModel {
    if (context.requestDirectoryItem.id == DirectoryItemIdModel.NONE)
      throw RepoWrongIdException(context.requestDirectoryItem.id.id)
    val model = context.requestDirectoryItem
    val flatId = model.id.asUUID()
    return transaction(db) {
      if (printLogs) addLogger(StdOutSqlLogger)
      val flatToUpdate = DirectoryDto[flatId]
      //??? -> save to db
      flatToUpdate.apply { of(model) }.toModel()
      context.responseDirectoryItem = DirectoryDto[flatId].toModel()
      context.responseDirectoryItem
    }
  }

  override suspend fun delete(context: BeContext): IDirectoryItemModel {
    val id = context.requestDirectoryItemId
    if (id == DirectoryItemIdModel.NONE) throw RepoWrongIdException(id.id)
    return transaction(db) {
      if (printLogs) addLogger(StdOutSqlLogger)
      val old = DirectoryDto[id.asUUID()]
      old.delete()
      //TODO cascade delete ???
      context.responseDirectoryItem = old.toModel()
      context.responseDirectoryItem
    }
  }
}
