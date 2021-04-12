package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.websocket

import io.ktor.http.cio.websocket.*
import io.ktor.server.testing.*
import kotlinx.coroutines.withTimeoutOrNull
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.jsonConfig
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.module
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IResponse
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ResponseStatusDto
import kotlin.test.Test
import kotlin.test.assertEquals

internal class WebsocketTest {
  @Test
  fun `bad json must fail`() {
    withTestApplication({ module(testing = true) }) {
      handleWebSocketConversation("/ws") { incoming, outgoing ->
        withTimeoutOrNull(250L) {
          while (true) {
            val responseJson = (incoming.receive() as Frame.Text).readText()
            println("GOT INIT RESPONSE: $responseJson")
          }
        }
        val requestJson = """{"type":"123"}"""
        outgoing.send(Frame.Text(requestJson))
        val responseJson = (incoming.receive() as Frame.Text).readText()
        println("RESPONSE: $responseJson")
        val response = jsonConfig.decodeFromString(Message.serializer(), responseJson) as IResponse
        assertEquals(ResponseStatusDto.BAD_REQUEST, response.status)
      }
    }
  }
}
