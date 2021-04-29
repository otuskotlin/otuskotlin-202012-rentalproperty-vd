package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra

import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.BeforeClass
import org.testcontainers.containers.GenericContainer
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.flats.FlatRepositoryCassandra
import java.time.Duration
import kotlin.test.Test
import kotlin.test.assertEquals

class CassandraContainer : GenericContainer<CassandraContainer>("cassandra")

internal class FlatCassandraTest {

  companion object {
    private val PORT = 9042
    private val keyspace = "test_keyspace"
    private lateinit var container: CassandraContainer
    private lateinit var repo: FlatRepositoryCassandra

    @BeforeClass
    @JvmStatic
    fun tearUp() {
      container = CassandraContainer()
        .withExposedPorts(PORT)
        .withStartupTimeout(Duration.ofSeconds(300L))
        .apply {
          start()
        }

      repo = FlatRepositoryCassandra(
        keyspaceName = keyspace,
        hosts = container.host,
        port = container.getMappedPort(PORT),
        testing = true,
        initObjects = listOf(
          FlatModel.STUB.copy(),
          FlatModel.STUB2.copy(),
          FlatModel.STUB3.copy(),
          FlatModel.STUB.copy(id = FlatIdModel("test-flat-id-4")),
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
  fun flatListTest() {
    runBlocking {
      val context = BeContext(
        flatFilter = FlatFilterModel(
          text = "нка",
          offset = 0,
          count = 10,
        )
      )
      val response = repo.list(context)
      println(response)
      assertEquals(response, context.responseFlats)
      assertEquals(1, context.pageCount)
      assertEquals(2, response.size)
    }
  }

  @Test
  fun flatReadTest() {
    runBlocking {
      val context = BeContext(
        requestFlatId = FlatIdModel("test-flat-id")
      )
      val model = repo.read(context)
      assertEquals(model, context.responseFlat)
      assertEquals(FlatModel.STUB.area, model.area)
      assertEquals(FlatModel.STUB.description, model.description)
    }
  }

  @Test
  fun flatCreateTest() {
    runBlocking {
      val flat = FlatModel.STUB.copy(id = FlatIdModel("test-flat-id-5"))
      val context = BeContext(
        requestFlat = flat
      )
      val result = repo.create(context)
      assertEquals(result, context.responseFlat)
      assertEquals(FlatModel.STUB.area, result.area)
      assertEquals(FlatModel.STUB.description, result.description)
      val context2 = BeContext(requestFlatId = result.id)
      repo.read(context2)
      assertEquals(FlatModel.STUB.area, context2.responseFlat.area)
      assertEquals(FlatModel.STUB.description, context2.responseFlat.description)
    }
  }

  @Test
  fun flatUpdateTest() {
    runBlocking {
      val flat = FlatModel.STUB.copy(
        id = FlatIdModel("test-flat-id"),
        beds = 5,
        bathrooms = 5,
        //description = "update description"
      )
      val context = BeContext(
        requestFlat = flat
      )
      val result = repo.update(context)
      println(result)
      assertEquals(result, context.responseFlat)
      assertEquals(FlatModel.STUB.area, result.area)
      assertEquals(5, result.beds)
      assertEquals(5, result.bathrooms)
      //assertEquals("update description", result.description)
      val context2 = BeContext(requestFlatId = FlatIdModel("test-flat-id"))
      repo.read(context2)
      assertEquals(FlatModel.STUB.area, context2.responseFlat.area)
      assertEquals(5, context2.responseFlat.beds)
      assertEquals(5, context2.responseFlat.bathrooms)
      //assertEquals("update description", context2.responseFlat.description)
    }
  }

  @Test
  fun flatDeleteTest() {
    runBlocking {
      val context = BeContext(
        requestFlatId = FlatIdModel("test-flat-id-4")
      )
      val model = repo.delete(context)
      assertEquals(model, context.responseFlat)
      assertEquals(FlatModel.STUB.description, model.description)
    }
  }
}
