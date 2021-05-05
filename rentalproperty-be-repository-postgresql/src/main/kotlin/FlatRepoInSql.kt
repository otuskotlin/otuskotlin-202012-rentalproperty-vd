import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoNotFoundException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoWrongIdException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IFlatRepository
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.IDirectoryItemModel
import schema.*
import java.sql.Connection
import java.util.*

class FlatRepoInSql(
  url: String = "jdbc:postgresql://localhost:5432/rentalproperty",
  driver: String = "org.postgresql.Driver",
  user: String = "postgres",
  password: String = "postgres",
  val printLogs: Boolean = false,
  initObjects: Collection<FlatModel> = emptyList()
) : IFlatRepository {

  private val db by lazy {
    val _db = Database.connect(
      url = url,
      driver = driver,
      user = user,
      password = password
    )
    transaction {
      SchemaUtils.create(FlatsTable)
    }
    _db
  }

  init {
    runBlocking {
      initObjects.forEach {
        createWithId(BeContext(requestFlat = it), true)
      }
    }
  }

  override suspend fun list(context: BeContext): Collection<FlatModel> {
    val filter = context.flatFilter
    return transaction(
      transactionIsolation = Connection.TRANSACTION_SERIALIZABLE,
      repetitionAttempts = 3,
      db = db
    ) {
      if (printLogs) addLogger(StdOutSqlLogger)
      val found =
        if (filter.text.isNotBlank()) {
          FlatDto.find {
            (FlatsTable.number like "%${filter.text}%") or (FlatsTable.description like "%${filter.text}%")
          }
        } else {
          FlatDto.all()
        }
      context.pageCount = found.count().toInt()
      found
        .limit(filter.count.takeIf { it > 0 } ?: 20,
          filter.offset.toLong().takeIf { it > 0 } ?: 0)
        .toList()
      context.responseFlats = found.map { it.toModel() }.toMutableList()
      context.responseFlats
    }
  }

  override suspend fun create(context: BeContext): FlatModel = createWithId(context)

  private suspend fun createWithId(context: BeContext, setId: Boolean = false): FlatModel {
    val model = context.requestFlat
    return transaction(db) {
      if (printLogs) addLogger(StdOutSqlLogger)
      val flatNew = FlatDto.new(
        if (setId) model.id.asUUID() else null
      ) {
        of(model).apply {
          bathroomType = getDirectoryDto(model.bathroomType.id.asUUID())
          repairType = getDirectoryDto(model.repairType.id.asUUID())
          viewFromWindow = getDirectoryDto(model.viewFromWindow.id.asUUID())
          conveniences = getListDirectoryDto(model.conveniences)
          appliances = getListDirectoryDto(model.appliances)
          photos = getListMediaFileDto(model.photos)
        }
      }
      flatNew.toModel()
      //val flatNewId = flatNew.id
      //DirectoryDto[model.bathroomType.id]
      //DirectoryRepoInSql.readById(model.bathroomType.id)
      //      model.conveniences.forEach {
      //        DirectoryDto.new {
      //          model.conveniences
      //        }
      //      }
      //FlatDto[flatNewId].toModel()
    }
  }

  private fun getDirectoryDto(id: UUID): DirectoryDto {
    if (id.equals(""))
      throw RepoWrongIdException(id.toString())
    else {
      val item = DirectoryDto.findById(id)
      if (item == null) {
        throw RepoNotFoundException(id.toString())
      } else {
        return item
      }
    }
  }

  private fun getListDirectoryDto(items: Set<IDirectoryItemModel>): SizedIterable<DirectoryDto> {
    val foundItems = DirectoryDto.find {
      (DirectoriesTable.id.inList(items.map { it.id.asUUID() }.toList()))
    }
    if (foundItems.empty())
      throw RepoNotFoundException(items.map { it.id }.toList().toString())
    else
      return foundItems
  }

  private fun getListMediaFileDto(items: Set<MediaFileModel>): SizedIterable<MediaFileDto> =
    MediaFileDto.find {
      (MediaFilesTable.id.inList(items.map { it.id.asUUID() }.toList()))
    }

  override suspend fun read(context: BeContext): FlatModel {
    val id = context.requestFlatId
    if (id == FlatIdModel.NONE) throw RepoWrongIdException(id.id)
    return transaction {
      if (printLogs) addLogger(StdOutSqlLogger)
      context.responseFlat = FlatDto[id.asUUID()].toModel()
      context.responseFlat
    }
  }

  override suspend fun update(context: BeContext): FlatModel {
    if (context.requestFlat.id == FlatIdModel.NONE)
      throw RepoWrongIdException(context.requestFlat.id.id)
    val model = context.requestFlat
    val flatId = model.id.asUUID()
    return transaction(db) {
      if (printLogs) addLogger(StdOutSqlLogger)
      val flatToUpdate = FlatDto[flatId]
      //??? -> save to db
      flatToUpdate
        .apply {
          of(model).apply {
            bathroomType = getDirectoryDto(model.bathroomType.id.asUUID())
            repairType = getDirectoryDto(model.repairType.id.asUUID())
            viewFromWindow = getDirectoryDto(model.viewFromWindow.id.asUUID())
            conveniences = getListDirectoryDto(model.conveniences)
            appliances = getListDirectoryDto(model.appliances)
            photos = getListMediaFileDto(model.photos)
          }
        }
        .toModel()
//      DirectoriesTable.deleteWhere { DirectoriesTable.id eq flatId }
//      model.conveniences.forEach {
//        DirectoryDto.new {
//          model.conveniences
//        }
//      }
      context.responseFlat = FlatDto[flatId].toModel()
      context.responseFlat
    }
  }

  override suspend fun delete(context: BeContext): FlatModel {
    val id = context.requestFlatId
    if (id == FlatIdModel.NONE) throw RepoWrongIdException(id.id)
    return transaction(db) {
      if (printLogs) addLogger(StdOutSqlLogger)
      val old = FlatDto[id.asUUID()]
      old.delete()
      //TODO cascade delete ???
      //DirectoriesTable.deleteWhere { DirectoriesTable.id eq old.id }
      context.responseFlat = old.toModel()
      context.responseFlat
    }
  }
}
