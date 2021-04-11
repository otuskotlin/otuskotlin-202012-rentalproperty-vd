package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.http.cio.websocket.*
import io.ktor.routing.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helpers.WsUserSession
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helpers.service
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.jsonConfig
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.AdvertFlatService
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.AdvertHouseService
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.FlatService
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.HouseService
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.toModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.EmptyUserSession
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import java.time.Instant
import java.util.*

private val sessions = mutableMapOf<WebSocketSession, WsUserSession>()

@OptIn(InternalSerializationApi::class)
fun Routing.rpWebSocket(
  houseService: HouseService,
  flatService: FlatService,
  advertHouseService: AdvertHouseService,
  advertFlatService: AdvertFlatService,
) {
  webSocket("/ws") { // websocketSession
    sessions[this] = WsUserSession(fwSession = this)
    apply {
      val ctx = BeContext(
        responseId = UUID.randomUUID().toString(),
        timeStarted = Instant.now(),
        userSession = sessions[this] ?: EmptyUserSession,
        status = BeContextStatus.RUNNING
      )
      service(
        context = ctx,
        query = null,
        houseService = houseService,
        flatService = flatService,
        advertHouseService = advertHouseService,
        advertFlatService = advertFlatService,
      )?.also {
        val responseJson = jsonConfig.encodeToString(Message::class.serializer(), it)
        outgoing.send(Frame.Text(responseJson))
      }
    }

    for (frame in incoming) {
      when (frame) {
        is Frame.Text -> {
          val ctx = BeContext(
            responseId = UUID.randomUUID().toString(),
            timeStarted = Instant.now(),
            userSession = sessions[this] ?: EmptyUserSession
          )
          try {
            val requestJson = frame.readText()
            val query = jsonConfig.decodeFromString(Message.serializer(), requestJson)
            ctx.status = BeContextStatus.RUNNING
            service(
              context = ctx,
              query = query,
              houseService = houseService,
              flatService = flatService,
              advertHouseService = advertHouseService,
              advertFlatService = advertFlatService,
            )?.also {
              val responseJson = jsonConfig.encodeToString(Message::class.serializer(), it)
              outgoing.send(Frame.Text(responseJson))
            }
          } catch (e: ClosedReceiveChannelException) {
            service(
              context = ctx,
              query = null,
              houseService = houseService,
              flatService = flatService,
              advertHouseService = advertHouseService,
              advertFlatService = advertFlatService,
            )
            sessions -= this
          } catch (e: Throwable) {
            e.printStackTrace()
            ctx.status = BeContextStatus.FAILING
            ctx.errors.add(e.toModel())
            service(
              context = ctx,
              query = null,
              houseService = houseService,
              flatService = flatService,
              advertHouseService = advertHouseService,
              advertFlatService = advertFlatService,
            )?.also {
              val responseJson = jsonConfig.encodeToString(Message::class.serializer(), it)
              outgoing.send(Frame.Text(responseJson))
            }
          }
        }
        else -> {
        }
      }
    }
  }
}
