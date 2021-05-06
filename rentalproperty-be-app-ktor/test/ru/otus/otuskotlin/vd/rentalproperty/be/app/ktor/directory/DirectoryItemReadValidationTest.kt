package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.directory

import io.ktor.http.*
import io.ktor.server.testing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.jsonConfig
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.module
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ResponseStatusDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.WorkModeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.AppliancesDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.RequestDirectoryItemRead
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.ResponseDirectoryItemRead
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class DirectoryItemReadValidationTest {

  @Test
  fun `non-empty directoryId must success`() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.directoryRead) {
        val body = RequestDirectoryItemRead(
          requestId = "request-id",
          directoryItem = AppliancesDto.STUB_AIR_CONDITIONER,
          debug = RequestDirectoryItemRead.Debug(
            mode = WorkModeDto.TEST,
            stubCase = RequestDirectoryItemRead.StubCase.SUCCESS
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
            as? ResponseDirectoryItemRead)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.SUCCESS, res.status)
        assertEquals("request-id", res.onRequest)
        assertEquals(AppliancesDto.STUB_AIR_CONDITIONER.name, res.directoryItem?.name)
      }
    }
  }

  @Test
  fun `empty directoryId must fail`() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.directoryRead) {
        val body = RequestDirectoryItemRead(
          requestId = "request-id",
          directoryItem = AppliancesDto(),
        )

        val bodyString = jsonConfig.encodeToString(Message.serializer(), body)
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
        val jsonString = response.content ?: fail("Null response json")
        println("RESPONSE JSON: $jsonString")

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString) as? ResponseDirectoryItemRead)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.BAD_REQUEST, res.status)
        assertEquals("request-id", res.onRequest)
      }
    }
  }

  @Test
  fun `bad json must fail`() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.directoryRead) {
        val bodyString = "{"
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
        val jsonString = response.content ?: fail("Null response json")
        println("RESPONSE JSON: $jsonString")

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString) as? ResponseDirectoryItemRead)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.BAD_REQUEST, res.status)
        assertTrue {
          res.errors?.find { it.message?.toLowerCase()?.contains("syntax") == true } != null
        }
      }
    }
  }
}
