package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.websocket.house

import io.ktor.http.cio.websocket.*
import io.ktor.server.testing.*
import kotlinx.coroutines.withTimeoutOrNull
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.jsonConfig
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.module
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.RequestHouseList
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.ResponseHouseList
import kotlin.test.Test
import kotlin.test.assertEquals

internal class WebsocketHouseListTest {
  @Test
  fun `get the list must be a success`() {
    withTestApplication({ module(testing = true) }) {
      handleWebSocketConversation("/ws") { incoming, outgoing ->
        val query = RequestHouseList(
          requestId = "123",
          debug = RequestHouseList.Debug(
            stubCase = RequestHouseList.StubCase.SUCCESS
          )
        )
        withTimeoutOrNull(250L) {
          while (true) {
            val respJson = (incoming.receive() as Frame.Text).readText()
            println("GOT INIT RESPONSE: $respJson")
          }
        }
        val requestJson = jsonConfig.encodeToString(Message.serializer(), query)
        outgoing.send(Frame.Text(requestJson))
        val respJson = (incoming.receive() as Frame.Text).readText()
        println("RESPONSE: $respJson")
        val response = jsonConfig.decodeFromString(Message.serializer(), respJson) as ResponseHouseList
        assertEquals("123", response.onRequest)
        assertEquals(1, response.houses?.size)
      }
    }
  }
}
