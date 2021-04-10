package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.house

import io.ktor.http.*
import io.ktor.server.testing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.jsonConfig
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.module
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ResponseStatusDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.WorkModeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.HouseListFilterDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.RequestHouseList
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.ResponseHouseList
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class HouseListValidationTest {

  @Test
  fun `non-empty list must success`() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.houseList) {
        val body = RequestHouseList(
          requestId = "321",
          filterData = HouseListFilterDto(

          ),
          debug = RequestHouseList.Debug(
            mode = WorkModeDto.TEST,
            stubCase = RequestHouseList.StubCase.SUCCESS
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

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString) as? ResponseHouseList)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.SUCCESS, res.status)
        assertEquals("321", res.onRequest)
        assertEquals(2, res.houses?.firstOrNull()?.floors)
      }
    }
  }

  @Test
  fun `bad json must fail`() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.houseList) {
        val bodyString = "{"
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
        val jsonString = response.content ?: fail("Null response json")
        println("RESPONSE JSON: $jsonString")

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString) as? ResponseHouseList)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.BAD_REQUEST, res.status)
        assertTrue {
          res.errors?.find { it.message?.toLowerCase()?.contains("syntax") == true } != null
        }
      }
    }
  }
}
