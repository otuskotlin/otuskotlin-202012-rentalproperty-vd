package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra

import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.BeforeClass
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseMaterialModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.house.HouseRepositoryCassandra
import java.time.Duration
import kotlin.test.Test
import kotlin.test.assertEquals

internal class HouseCassandraTest {

  companion object {
    private val PORT = 9042
    private val keyspace = "test_keyspace"
    private lateinit var container: CassandraContainer
    private lateinit var repo: HouseRepositoryCassandra

    @BeforeClass
    @JvmStatic
    fun tearUp() {
      container = CassandraContainer()
        .withExposedPorts(PORT)
        .withStartupTimeout(Duration.ofSeconds(300L))
        .apply {
          start()
        }

      repo = HouseRepositoryCassandra(
        keyspaceName = keyspace,
        hosts = container.host,
        port = container.getMappedPort(PORT),
        testing = true,
        initObjects = listOf(
          HouseModel.STUB_SINGLE_HOUSE,
          HouseModel.STUB_MULTI_APARTMENT,
          HouseModel.STUB_SINGLE_HOUSE.copy(id = HouseIdModel("test-house-id-3")),
          HouseModel.STUB_MULTI_APARTMENT.copy(id = HouseIdModel("test-house-id-4")),
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
  fun houseListTest() {
    runBlocking {
      val context = BeContext(
        houseFilter = HouseFilterModel(
          text = "address",
          offset = 0,
          count = 10,
        )
      )
      val response = repo.list(context)
      println(response)
      assertEquals(response, context.responseHouses)
      assertEquals(1, context.pageCount)
      assertEquals(4, response.size)
    }
  }

  @Test
  fun houseReadTest() {
    runBlocking {
      val context = BeContext(
        requestHouseId = HouseIdModel("test-house-id-1")
      )
      val result = repo.read(context)
      assertEquals(result, context.responseHouse)
      assertEquals(HouseModel.STUB_SINGLE_HOUSE.area, result.area)
      assertEquals(HouseModel.STUB_SINGLE_HOUSE.address, result.address)
    }
  }

  @Test
  fun houseCreateTest() {
    runBlocking {
      val house = HouseModel.STUB_SINGLE_HOUSE.copy(
        material = HouseMaterialModel.STUB_PANEL,
        yearConstruction = 1950,
      )
      val context = BeContext(
        requestHouse = house
      )
      val result = repo.create(context)
      assertEquals(result, context.responseHouse)
      assertEquals(house.material, result.material)
      assertEquals(house.yearConstruction, result.yearConstruction)
      val context2 = BeContext(requestHouseId = result.id)
      repo.read(context2)
      assertEquals(house.material, context2.responseHouse.material)
      assertEquals(house.yearConstruction, context2.responseHouse.yearConstruction)
    }
  }

  @Test
  fun houseUpdateTest() {
    runBlocking {
      val house = HouseModel.STUB_SINGLE_HOUSE.copy(
        id = HouseIdModel("test-house-id-3"),
        type = HouseTypeModel.STUB_MULTI_APARTMENT,
        series = "UpdateSeries",
      )
      val context = BeContext(
        requestHouse = house
      )
      val result = repo.update(context)
      println(result)
      assertEquals(result, context.responseHouse)
      assertEquals(house.type, result.type)
      assertEquals(house.series, result.series)
      val context2 = BeContext(requestHouseId = house.id)
      repo.read(context2)
      assertEquals(house.type, context2.responseHouse.type)
      assertEquals(house.series, context2.responseHouse.series)
    }
  }

  @Test
  fun houseDeleteTest() {
    runBlocking {
      val context = BeContext(
        requestHouseId = HouseIdModel("test-house-id-4")
      )
      val result = repo.delete(context)
      assertEquals(result, context.responseHouse)
      assertEquals(HouseModel.STUB_MULTI_APARTMENT.area, result.area)
    }
  }
}
