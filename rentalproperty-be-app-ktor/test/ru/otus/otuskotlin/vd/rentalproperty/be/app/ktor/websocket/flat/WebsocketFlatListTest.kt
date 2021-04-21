package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.websocket.flat

import io.ktor.http.cio.websocket.*
import io.ktor.server.testing.*
import kotlinx.coroutines.withTimeoutOrNull
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.jsonConfig
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.module
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.RequestFlatList
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.ResponseFlatList
import kotlin.test.Test
import kotlin.test.assertEquals

internal class WebsocketFlatListTest {
  @Test
  fun `get the list must be a success`() {
    withTestApplication({ module(testing = true) }) {
      handleWebSocketConversation("/ws") { incoming, outgoing ->
        val query = RequestFlatList(
          requestId = "123",
          debug = RequestFlatList.Debug(
            stubCase = RequestFlatList.StubCase.SUCCESS
          )
        )
        withTimeoutOrNull(250L) {
          while (true) {
            val responseJson = (incoming.receive() as Frame.Text).readText()
            println("GOT INIT RESPONSE: $responseJson")
          }
        }
        val requestJson = jsonConfig.encodeToString(Message.serializer(), query)
        outgoing.send(Frame.Text(requestJson))
        val responseJson = (incoming.receive() as Frame.Text).readText()
        println("RESPONSE: $responseJson")
        val response = jsonConfig.decodeFromString(Message.serializer(), responseJson) as ResponseFlatList
        assertEquals("123", response.onRequest)
        assertEquals(1, response.flats?.size)
      }
    }
  }
}
