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
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.RequestFlatRead
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.ResponseFlatRead
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class FlatReadValidationTest {

  @Test
  fun `non-empty flatId must success`() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.flatRead) {
        val body = RequestFlatRead(
          requestId = "321",
          flatId = "test-flat-id",
          debug = RequestFlatRead.Debug(
            mode = WorkModeDto.TEST,
            stubCase = RequestFlatRead.StubCase.SUCCESS
          )
        )

        val bodyString = jsonConfig.encodeToString(Message.serializer(), body)
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(
          ContentType.Application.Json.withCharset(Charsets.UTF_8),
          response.contentType()
        )
        val jsonString = response.content ?: fail("Null response json")
        println("RESPONSE JSON: $jsonString")

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString)
            as? ResponseFlatRead)
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
  fun `empty flatId must fail`() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.flatRead) {
        val body = RequestFlatRead(
          requestId = "321",
          flatId = "",
        )

        val bodyString = jsonConfig.encodeToString(Message.serializer(), body)
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
        val jsonString = response.content ?: fail("Null response json")
        println("RESPONSE JSON: $jsonString")

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString) as? ResponseFlatRead)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.BAD_REQUEST, res.status)
        assertEquals("321", res.onRequest)
      }
    }
  }

  @Test
  fun `bad json must fail`() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.flatRead) {
        val bodyString = "{"
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
        val jsonString = response.content ?: fail("Null response json")
        println("RESPONSE JSON: $jsonString")

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString) as? ResponseFlatRead)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.BAD_REQUEST, res.status)
        assertTrue {
          res.errors?.find { it.message?.toLowerCase()?.contains("syntax") == true } != null
        }
      }
    }
  }
}
