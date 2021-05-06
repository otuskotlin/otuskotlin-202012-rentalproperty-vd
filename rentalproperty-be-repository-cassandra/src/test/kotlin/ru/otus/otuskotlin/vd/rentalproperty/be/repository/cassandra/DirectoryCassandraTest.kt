package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra

import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.BeforeClass
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.*
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.directory.DirectoryRepositoryCassandra
import java.time.Duration
import kotlin.test.Test
import kotlin.test.assertEquals

internal class DirectoryCassandraTest {

  companion object {
    private val PORT = 9042
    private val keyspace = "test_keyspace"
    private lateinit var container: CassandraContainer
    private lateinit var repo: DirectoryRepositoryCassandra

    @BeforeClass
    @JvmStatic
    fun tearUp() {
      container = CassandraContainer()
        .withExposedPorts(PORT)
        .withStartupTimeout(Duration.ofSeconds(300L))
        .apply {
          start()
        }

      repo = DirectoryRepositoryCassandra(
        keyspaceName = keyspace,
        hosts = container.host,
        port = container.getMappedPort(PORT),
        testing = true,
        initObjects = listOf(
          DirectoryItemModel.STUB,
          AppliancesModel.STUB_AIR_CONDITIONER,
          AppliancesModel.STUB_KITCHEN_STOVE,
          BathroomTypeModel.STUB_COMBINED,
          ConvenienceModel.STUB_GAS,
          ConvenienceModel.STUB_FURNITURE_IN_KITCHEN,
          HouseMaterialModel.STUB_BRICK,
          HouseMaterialModel.STUB_PANEL,
          HouseTypeModel.STUB_SINGLE_HOUSE,
          HouseTypeModel.STUB_MULTI_APARTMENT,
          InfrastructureModel.STUB_ELECTRICITY,
          InfrastructureModel.STUB_SEWERAGE,
          InfrastructureModel.STUB_WATER,
        )
      ).init()
    }

    @AfterClass
    @JvmStatic
    fun tearDown() {
      container.close()
    }
  }

  @Test
  fun directoryListTest() {
    runBlocking {
      val context = BeContext(
        directoryFilter = DirectoryFilterModel(
          text = "HouseType",
          offset = 0,
          count = 10,
        )
      )
      val response = repo.list(context)
      println(response)
      assertEquals(response, context.responseDirectoryItems)
      assertEquals(1, context.pageCount)
      assertEquals(2, response.size)
    }
  }

  @Test
  fun directoryReadTest() {
    runBlocking {
      val context = BeContext(
        requestDirectoryItemId = DirectoryItemIdModel("test-a-id-1")
      )
      val result = repo.read(context)
      assertEquals(result, context.responseDirectoryItem)
      assertEquals(AppliancesModel.STUB_AIR_CONDITIONER.name, result.name)
    }
  }

  @Test
  fun directoryCreateTest() {
    runBlocking {
      val directory = BathroomTypeModel(
        name = "SEPARATED"
      )
      val context = BeContext(
        requestDirectoryItem = directory
      )
      val result = repo.create(context)
      assertEquals(result, context.responseDirectoryItem)
      assertEquals(directory.name, result.name)
      val context2 = BeContext(requestDirectoryItemId = result.id)
      repo.read(context2)
      assertEquals(directory.name, context2.responseDirectoryItem.name)
    }
  }

  @Test
  fun directoryUpdateTest() {
    runBlocking {
      val directory = ConvenienceModel.STUB_GAS.copy(
        name = "UpdateGAS",
      )
      val context = BeContext(
        requestDirectoryItem = directory
      )
      val result = repo.update(context)
      println(result)
      assertEquals(result, context.responseDirectoryItem)
      assertEquals(directory.name, result.name)
      val context2 = BeContext(requestDirectoryItemId = directory.id)
      repo.read(context2)
      assertEquals(directory.name, context2.responseDirectoryItem.name)
    }
  }

  @Test
  fun directoryDeleteTest() {
    runBlocking {
      val context = BeContext(
        requestDirectoryItemId = InfrastructureModel.STUB_WATER.id
      )
      val result = repo.delete(context)
      assertEquals(result, context.responseDirectoryItem)
      assertEquals(InfrastructureModel.STUB_WATER.name, result.name)
    }
  }
}
