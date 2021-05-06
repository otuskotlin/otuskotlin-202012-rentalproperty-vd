package ru.otus.otuskotlin.vd.rentalproperty.be.repository.postgresql

import org.testcontainers.containers.PostgreSQLContainer
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import java.time.Duration

class PostgresContainer : PostgreSQLContainer<PostgresContainer>("postgres:13.2")

object SqlTestCompanion {
  private const val USER = "postgres"
  private const val PASS = "postgres"
  private const val DB = "rentalproperty"

  val testFlatId1 = FlatIdModel("11111111-1111-1111-1111-F11111111111")
  val testFlatId2 = FlatIdModel("11111111-1111-1111-1111-F11111111112")
  val testFlatId3 = FlatIdModel("11111111-1111-1111-1111-F11111111113")
  val testFlatId4 = FlatIdModel("11111111-1111-1111-1111-F11111111114")
  val testFlatId5 = FlatIdModel("11111111-1111-1111-1111-F11111111115")
  val expectedFlatId = FlatIdModel("11111111-1111-1111-1111-F11111111120")

  private val container by lazy {
    PostgresContainer().apply {
      withUsername(USER)
      withPassword(PASS)
      withDatabaseName(DB)
      withStartupTimeout(Duration.ofSeconds(300L))
      start()
    }
  }

  val url by lazy { container.jdbcUrl }

  val repoFlat by lazy {
    FlatRepoSql(
      url = url,
      user = USER,
      password = PASS,
      printLogs = true,
      //      keyspaceName = keyspace,
      //      hosts = container.host,
      //      port = container.getMappedPort(PORT),
      //      testing = true,
      initObjects = listOf(
        FlatModel(
          id = testFlatId1,
          number = "number-flat1",
          description = "to-update-flat1",
        ),
        FlatModel(
          id = testFlatId2,
          number = "number-flat2",
          description = "test-list-flat2",
        ),
        FlatModel(
          id = testFlatId3,
          number = "number-flat3",
          description = "flat3",
        ),
        FlatModel(
          id = testFlatId4,
          number = "number-flat4",
          description = "test-list-flat4",
        ),
        FlatModel(
          id = testFlatId5,
          number = "number-flat5",
          description = "flat-to-delete",
        ),
      )
    )
  }
}
