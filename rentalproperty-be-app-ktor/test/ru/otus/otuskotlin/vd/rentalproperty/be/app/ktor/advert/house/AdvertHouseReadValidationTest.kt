package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.advert.house

import io.ktor.http.*
import io.ktor.server.testing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.jsonConfig
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.module
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.RequestAdvertHouseRead
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.ResponseAdvertHouseRead
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ResponseStatusDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.WorkModeDto
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class AdvertHouseReadValidationTest {

  @Test
  fun `non-empty advertId must success`() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Post, RestEndpoints.advertHouseRead) {
        val body = RequestAdvertHouseRead(
          requestId = "321",
          advertId = "test-advert-id",
          debug = RequestAdvertHouseRead.Debug(
            mode = WorkModeDto.TEST,
            stubCase = RequestAdvertHouseRead.StubCase.SUCCESS
          )
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
        println("RESPONSE JSON: $jsonString")

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString)
            as? ResponseAdvertHouseRead)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.SUCCESS, res.status)
        assertEquals("321", res.onRequest)
        assertEquals("Продаётся дом", res.advert?.name)
      }
    }
  }
}
