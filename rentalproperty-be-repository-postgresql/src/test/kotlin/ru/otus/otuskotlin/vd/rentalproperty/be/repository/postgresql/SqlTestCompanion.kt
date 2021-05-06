package ru.otus.otuskotlin.vd.rentalproperty.be.repository.postgresql

import org.testcontainers.containers.PostgreSQLContainer
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.*
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

  val testDirectoryAppliancesId1 = DirectoryItemIdModel("11111111-1111-1111-1111-DA1111111111")
  val testDirectoryAppliancesId2 = DirectoryItemIdModel("11111111-1111-1111-1111-DA1111111112")
  val testDirectoryAppliancesId3 = DirectoryItemIdModel("11111111-1111-1111-1111-DA111111113")
  val testDirectoryBathroomTypeId1 = DirectoryItemIdModel("11111111-1111-1111-1111-DB1111111111")
  val testDirectoryBathroomTypeId2 = DirectoryItemIdModel("11111111-1111-1111-1111-DB1111111112")
  val testDirectoryBathroomTypeId3 = DirectoryItemIdModel("11111111-1111-1111-1111-DB1111111113")
  val testDirectoryRepairTypeId1 = DirectoryItemIdModel("11111111-1111-1111-1111-DF1111111111")
  val testDirectoryRepairTypeId2 = DirectoryItemIdModel("11111111-1111-1111-1111-DF1111111112")
  val testDirectoryRepairTypeId3 = DirectoryItemIdModel("11111111-1111-1111-1111-DF1111111113")
  val testDirectoryViewFromWindowId1 = DirectoryItemIdModel("11111111-1111-1111-1111-DE1111111111")
  val testDirectoryViewFromWindowId2 = DirectoryItemIdModel("11111111-1111-1111-1111-DE1111111112")
  val testDirectoryViewFromWindowId3 = DirectoryItemIdModel("11111111-1111-1111-1111-DE1111111113")
  val testDirectoryConvenienceId1 = DirectoryItemIdModel("11111111-1111-1111-1111-DC1111111111")
  val testDirectoryConvenienceId2 = DirectoryItemIdModel("11111111-1111-1111-1111-DC1111111112")
  val testDirectoryConvenienceId3 = DirectoryItemIdModel("11111111-1111-1111-1111-DC1111111113")
  val expectedDirectoryItemId = DirectoryItemIdModel("11111111-1111-1111-1111-DEE111111113")

  val testPhotosId1 = MediaFileIdModel("11111111-1111-1111-1111-FF1111111111")
  val testPhotosId2 = MediaFileIdModel("11111111-1111-1111-1111-FF1111111112")
  val testPhotosId3 = MediaFileIdModel("11111111-1111-1111-1111-FF1111111113")

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

  val repoDirectory by lazy {
    DirectoryRepoSql(
      url = url,
      user = USER,
      password = PASS,
      printLogs = true,
      initObjects = listOf(
        AppliancesModel.STUB_AIR_CONDITIONER.copy(
          id = testDirectoryAppliancesId1
        ),
        AppliancesModel.STUB_KITCHEN_STOVE.copy(
          id = testDirectoryAppliancesId2
        ),
        AppliancesModel.STUB_INTERNET.copy(
          id = testDirectoryAppliancesId3
        ),
        BathroomTypeModel.STUB_COMBINED.copy(
          id = testDirectoryBathroomTypeId1
        ),
        RepairTypeModel.STUB_RENOVATION.copy(
          id = testDirectoryRepairTypeId1
        ),
        ViewFromWindowModel.STUB_PARK.copy(
          id = testDirectoryViewFromWindowId1
        ),
        ConvenienceModel.STUB_GAS.copy(
          id = testDirectoryConvenienceId1
        ),
        ConvenienceModel.STUB_FURNITURE_IN_KITCHEN.copy(
          id = testDirectoryConvenienceId2
        ),
        ConvenienceModel.STUB_PARKING.copy(
          id = testDirectoryConvenienceId3
        ),
      )
    )
  }
}
