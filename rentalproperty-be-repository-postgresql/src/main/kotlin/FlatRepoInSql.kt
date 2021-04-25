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
    transaction { SchemaUtils.create(FlatsTable) }
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
    val textFilter = context.flatFilter.text
//        if (textFilter.length < 3) throw RepoIndexException(textFilter)
    return transaction(db) {
      if (printLogs) addLogger(StdOutSqlLogger)
      context.responseFlats = FlatDto.all().map { it.toModel() }.toMutableList()
      context.responseFlats
    }
  }

  override suspend fun create(context: BeContext): FlatModel = createWithId(context)

  private suspend fun createWithId(context: BeContext, setId: Boolean = false): FlatModel {
    val model = context.requestFlat
    return transaction(db) {
      if (printLogs) addLogger(StdOutSqlLogger)
      val flatNew = FlatDto.new(model.id.asUUID().takeIf { setId }) {
        avatar = model.avatar
        title = model.title
        description = model.description
      }
      val flatNewId = flatNew.id
      model.tagIds.forEach {
        FlatTagDto.new {
          this.tagId = it
          this.flat = flatNew
        }
      }
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
    if (context.requestFlat.id == FlatIdModel.NONE) throw RepoWrongIdException(context.requestFlat.id.id)
    val model = context.requestFlat
    val flatId = model.id.asUUID()
    return transaction(db) {
      if (printLogs) addLogger(StdOutSqlLogger)
      val flatToUpdate = FlatDto[flatId]
      flatToUpdate
        .apply { of(model) }
        .toModel()
      FlatsTagsTable.deleteWhere { FlatsTagsTable.flat eq flatId }
      model.tagIds.forEach {
        FlatTagDto.new {
          this.tagId = it
          this.flat = flatToUpdate
        }
      }
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
      context.responseFlat = old.toModel()
      context.responseFlat
    }
  }
}
