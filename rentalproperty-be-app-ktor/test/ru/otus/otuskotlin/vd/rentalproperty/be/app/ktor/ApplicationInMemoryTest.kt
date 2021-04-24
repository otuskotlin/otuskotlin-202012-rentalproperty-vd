package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor

import io.ktor.http.*
import io.ktor.server.testing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.realty.FlatRepoInMemory
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ResponseStatusDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.WorkModeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@OptIn(ExperimentalTime::class)
internal class ApplicationInMemoryTest {
  companion object {
    val flat1 = FlatModel.STUB
    val flat2 = FlatModel.STUB2
    val flat3 = FlatModel.STUB3

    val flatRepo by lazy {
      FlatRepoInMemory(
        ttl = 15.toDuration(DurationUnit.MINUTES),
        initObjects = listOf(flat1, flat2, flat3)
      )
    }
  }

  @Test
  fun testRead() {
    withTestApplication({ module(testFlatRepo = flatRepo) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.flatRead) {
        val body = RequestFlatRead(
          requestId = "request-id",
          flatId = flat1.id.id,
          debug = RequestFlatRead.Debug(mode = WorkModeDto.TEST)
        )

        val format = jsonConfig
        val bodyString = format.encodeToString(Message.serializer(), body)
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
        val jsonString = response.content ?: fail("Null response json")
        println(jsonString)

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString) as? ResponseFlatRead)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.SUCCESS, res.status)
        assertEquals("request-id", res.onRequest)
        assertEquals(flat1.number, res.flat?.number)
        assertEquals(flat1.area, res.flat?.area)
        assertEquals(flat1.description, res.flat?.description)
      }
    }
  }

  @Test
  fun testCreate() {
    withTestApplication({ module(testFlatRepo = flatRepo) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.flatCreate) {
        val body = RequestFlatCreate(
          requestId = "request-id",
          createData = FlatCreateDto.STUB2,
          debug = RequestFlatCreate.Debug(mode = WorkModeDto.TEST)
        )

        val format = jsonConfig
        val bodyString = format.encodeToString(Message.serializer(), body)
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
        val jsonString = response.content ?: fail("Null response json")
        println(jsonString)

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString) as? ResponseFlatCreate)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.SUCCESS, res.status)
        assertEquals("request-id", res.onRequest)
        assertEquals(flat2.number, res.flat?.number)
        assertEquals(flat2.area, res.flat?.area)
        assertEquals(flat2.description, res.flat?.description)
      }
    }
  }

  @Test
  fun testUpdate() {
    withTestApplication({ module(testFlatRepo = flatRepo) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.flatUpdate) {
        val body = RequestFlatUpdate(
          requestId = "request-id",
          updateData = FlatUpdateDto.STUB3,
          debug = RequestFlatUpdate.Debug(mode = WorkModeDto.TEST)
        )

        val format = jsonConfig
        val bodyString = format.encodeToString(Message.serializer(), body)
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
        val jsonString = response.content ?: fail("Null response json")
        println(jsonString)

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString) as? ResponseFlatUpdate)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.SUCCESS, res.status)
        assertEquals("request-id", res.onRequest)
        assertEquals(flat3.id.id, res.flat?.id)
        assertEquals(flat3.number, res.flat?.number)
        assertEquals(flat3.area, res.flat?.area)
        assertEquals(flat3.description, res.flat?.description)
      }
    }
  }

  @Test
  fun testDelete() {
    withTestApplication({ module(testFlatRepo = flatRepo) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.flatDelete) {
        val body = RequestFlatDelete(
          requestId = "request-id",
          flatId = flat2.id.id,
          debug = RequestFlatDelete.Debug(mode = WorkModeDto.TEST)
        )

        val format = jsonConfig

        val bodyString = format.encodeToString(Message.serializer(), body)
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
        val jsonString = response.content ?: fail("Null response json")
        println(jsonString)

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString) as? ResponseFlatDelete)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.SUCCESS, res.status)
        assertEquals("request-id", res.onRequest)
        assertEquals(flat2.id.id, res.flat?.id)
        assertEquals(flat2.number, res.flat?.number)
        assertEquals(flat2.area, res.flat?.area)
        assertEquals(flat2.description, res.flat?.description)
      }
    }
  }

  @Test
  fun testList() {
    withTestApplication({ module(testFlatRepo = flatRepo) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.flatList) {
        val body = RequestFlatList(
          requestId = "request-id",
          filter = FlatFilterDto(
            text = "нка",
            includeDescription = true,
            offset = 0,
            count = 10,
          ),
          debug = RequestFlatList.Debug(mode = WorkModeDto.TEST)
        )

        val format = jsonConfig

        val bodyString = format.encodeToString(Message.serializer(), body)
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
        val jsonString = response.content ?: fail("Null response json")
        println(jsonString)

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString) as? ResponseFlatList)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.SUCCESS, res.status)
        assertEquals("request-id", res.onRequest)
        assertEquals(2, res.flats?.size)
        assertEquals(1, res.pageCount)
      }
    }
  }
}
