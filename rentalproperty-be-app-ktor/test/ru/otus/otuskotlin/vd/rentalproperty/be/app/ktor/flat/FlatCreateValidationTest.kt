package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.flat

import io.ktor.http.*
import io.ktor.server.testing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.jsonConfig
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.module
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ResponseStatusDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.WorkModeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.ViewFromWindowDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.FlatCreateDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.RequestFlatCreate
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.ResponseFlatCreate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class FlatCreateValidationTest {

  @Test
  fun `non-empty create must success`() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.flatCreate) {
        val body = RequestFlatCreate(
          requestId = "321",
          createData = FlatCreateDto.STUB,
          debug = RequestFlatCreate.Debug(
            mode = WorkModeDto.TEST,
            stubCase = RequestFlatCreate.StubCase.SUCCESS
          )
        )

        val bodyString = jsonConfig.encodeToString(Message.serializer(), body)
        println("REQUEST JSON: $bodyString")
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
        val jsonString = response.content ?: fail("Null response json")
        println("RESPONSE JSON: $jsonString")

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString) as? ResponseFlatCreate)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.SUCCESS, res.status)
        assertEquals("321", res.onRequest)
        assertEquals(2, res.flat?.rooms)
        assertEquals(3, res.flat?.floor)
        assertEquals(ViewFromWindowDto.STUB_PARK, res.flat?.viewFromWindow)
      }
    }
  }

  @Test
  fun `empty title or description must fail`() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.flatCreate) {
        val body = RequestFlatCreate(
          requestId = "321",
          createData = FlatCreateDto()
        )

        val bodyString = jsonConfig.encodeToString(Message.serializer(), body)
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
        val jsonString = response.content ?: fail("Null response json")
        println("RESPONSE JSON: $jsonString")

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString) as? ResponseFlatCreate)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.BAD_REQUEST, res.status)
        assertEquals("321", res.onRequest)
        assertTrue {
          res.errors?.firstOrNull {
            it.message?.contains("title") == true
                && it.message?.contains("empty") == true
          } != null
        }
        assertTrue {
          res.errors?.firstOrNull {
            it.message?.contains("description") == true
                && it.message?.contains("empty") == true
          } != null
        }
      }
    }
  }

  @Test
  fun `bad json must fail`() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.flatCreate) {
        val bodyString = "{"
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
        val jsonString = response.content ?: fail("Null response json")
        println("RESPONSE JSON: $jsonString")

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString) as? ResponseFlatCreate)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.BAD_REQUEST, res.status)
        assertTrue {
          res.errors?.find { it.message?.toLowerCase()?.contains("syntax") == true } != null
        }
      }
    }
  }
}
