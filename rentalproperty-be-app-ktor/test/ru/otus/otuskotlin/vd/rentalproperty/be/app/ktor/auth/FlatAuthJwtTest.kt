package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.auth

import io.ktor.config.*
import io.ktor.http.*
import io.ktor.server.testing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.jsonConfig
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.module
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ResponseStatusDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.RequestFlatRead
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.ResponseFlatRead
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class FlatAuthJwtTest() : AuthJwtTest() {
  @Test
  fun `jwt auth flat read must success`() {
    println(TOKEN)
    withTestApplication({
      (environment.config as MapApplicationConfig).apply {
        put("rentalproperty.auth.jwt.optional", OPTIONAL)
        put("rentalproperty.auth.jwt.secret", SECRET)
        put("rentalproperty.auth.jwt.audience", AUDIENCE)
        put("rentalproperty.auth.jwt.domain", DOMAIN)
        put("rentalproperty.auth.jwt.realm", REALM)
      }
      module(testing = true)
    }) {
      val flat = FlatModel.STUB
      handleRequest(HttpMethod.Post, RestEndpoints.flatRead) {
        val body = RequestFlatRead(
          requestId = "321",
          flatId = flat.id.id,
          debug = RequestFlatRead.Debug(stubCase = RequestFlatRead.StubCase.SUCCESS)
        )
        val bodyString = jsonConfig.encodeToString(Message.serializer(), body)
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
        addHeader("Authorization", "Bearer $TOKEN")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
        val jsonString = response.content ?: fail("Null response json")
        println(jsonString)

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString) as? ResponseFlatRead)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.SUCCESS, res.status)
        assertEquals("321", res.onRequest)
        assertEquals(flat.description, res.flat?.description)
      }
    }
  }

  @Test
  fun `bad secret token for jwt auth flat read must fail`() {
    println(TOKEN)
    withTestApplication({
      (environment.config as MapApplicationConfig).apply {
        put("rentalproperty.auth.jwt.optional", OPTIONAL)
        put("rentalproperty.auth.jwt.secret", "bad-secret")
        put("rentalproperty.auth.jwt.audience", AUDIENCE)
        put("rentalproperty.auth.jwt.domain", DOMAIN)
        put("rentalproperty.auth.jwt.realm", REALM)
      }
      module(testing = true)
    }) {
      handleRequest(HttpMethod.Post, RestEndpoints.flatRead) {
        val body = RequestFlatRead(
          requestId = "321",
          flatId = "flat-id",
          debug = RequestFlatRead.Debug(stubCase = RequestFlatRead.StubCase.SUCCESS)
        )
        val bodyString = jsonConfig.encodeToString(Message.serializer(), body)
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
        addHeader("Authorization", "Bearer $TOKEN")
      }.apply {
        assertEquals(HttpStatusCode.Unauthorized, response.status())
      }
    }
  }
}
