import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoWrongIdException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IFlatRepository
import schema.FlatDto
import schema.FlatsTable
import java.sql.Connection

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
      val flatNew = FlatDto.new(if (setId) model.id.asUUID() else null) { model }
//        houseId = model.houseId.id
//        number = model.number
//        area = model.area
//        areaLiving = model.areaLiving
//        areaKitchen = model.areaKitchen
//        rooms = model.rooms
//        floor = model.floor
//        ceilingHeight = model.ceilingHeight
//        bedrooms = model.bedrooms
//        beds = model.beds
//        bathroom = model.bathroom
//        //bathroomType = DirectoryDto.of(model.bathroomType)
//        balcony = model.balcony
//        loggia = model.loggia
//        //repairType = DirectoryDto.of(model.repairType)
//        //viewFromWindow = DirectoryDto.of(model.viewFromWindow)
//        //conveniences = DirectoryDto.of(model.conveniences)
//        //appliances = DirectoryDto.of(model.appliances)
//        residents = model.residents
//        noSmoking = model.noSmoking
//        noAnimals = model.noAnimals
//        noChildren = model.noChildren
//        noParties = model.noParties
//        description = model.description
//        //photos = MediaFileDto.of(model.photos)
//      }
      val flatNewId = flatNew.id
      //TODO need find DirectoryItem
      //DirectoryDto[model.bathroomType.id]
      //DirectoryRepoInSql.readById(model.bathroomType.id)
//      model.conveniences.forEach {
//        DirectoryDto.new {
//          model.conveniences
//        }
//      }
      FlatDto[flatNewId].toModel()
    }
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
        .apply { of(model) }
        .toModel()
      //TODO need find DirectoryItem
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
