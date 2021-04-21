package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helpers

import io.ktor.http.cio.websocket.*
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IUserSession

class WsUserSession(
  override val fwSession: WebSocketSession
) : IUserSession<WebSocketSession> {
}
