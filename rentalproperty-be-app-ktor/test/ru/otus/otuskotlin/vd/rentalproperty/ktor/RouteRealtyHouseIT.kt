package ru.otus.otuskotlin.vd.rentalproperty.ktor

import io.ktor.http.*
import io.ktor.server.testing.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ResponseStatusDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.realty.house.RequestHouseRead
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.realty.house.ResponseHouseRead
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class RouteRealtyHouseIT {

  @Test
  fun testGet() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Post, "/realty/houses/get") {
        val body = RequestHouseRead(
          requestId = "321",
          houseId = "test-id",
        )

        val format = jsonConfig

        val bodyString = format.encodeToString(Message.serializer(), body)
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(
          ContentType.Application.Json.withCharset(Charsets.UTF_8),
          response.contentType()
        )
        val jsonString = response.content ?: fail("Null response json")
        println(jsonString)

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString)
            as? ResponseHouseRead)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.SUCCESS, res.status)
        assertEquals("321", res.onRequest)
        assertEquals("Novosibirsk", res.house?.address)
      }
    }
  }
}
