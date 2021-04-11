package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.application.*
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
import java.time.Duration
import java.time.Instant
import java.util.*

private val sessions = mutableMapOf<WebSocketSession, WsUserSession>()

fun Application.websocketEndpoints(
  flatService: FlatService,
  houseService: HouseService,
  advertFlatService: AdvertFlatService,
  advertHouseService: AdvertHouseService,
) {
  install(WebSockets) {
    pingPeriod = Duration.ofSeconds(60) // Disabled (null) by default
    timeout = Duration.ofSeconds(15)
    maxFrameSize = Long.MAX_VALUE // Disabled (max value). The connection will be closed if surpassed this length.
    masking = false
  }

  routing {
    rpWebSocket(
      flatService = flatService,
      houseService = houseService,
      advertFlatService = advertFlatService,
      advertHouseService = advertHouseService,
    )
  }
}

@OptIn(InternalSerializationApi::class)
fun Routing.rpWebSocket(
  flatService: FlatService,
  houseService: HouseService,
  advertFlatService: AdvertFlatService,
  advertHouseService: AdvertHouseService,
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
        flatService = flatService,
        houseService = houseService,
        advertFlatService = advertFlatService,
        advertHouseService = advertHouseService,
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
              flatService = flatService,
              houseService = houseService,
              advertFlatService = advertFlatService,
              advertHouseService = advertHouseService,
            )?.also {
              val responseJson = jsonConfig.encodeToString(Message::class.serializer(), it)
              outgoing.send(Frame.Text(responseJson))
            }
          } catch (e: ClosedReceiveChannelException) {
            service(
              context = ctx,
              query = null,
              flatService = flatService,
              houseService = houseService,
              advertFlatService = advertFlatService,
              advertHouseService = advertHouseService,
            )
            sessions -= this
          } catch (e: Throwable) {
            e.printStackTrace()
            ctx.status = BeContextStatus.FAILING
            ctx.errors.add(e.toModel())
            service(
              context = ctx,
              query = null,
              flatService = flatService,
              houseService = houseService,
              advertFlatService = advertFlatService,
              advertHouseService = advertHouseService,
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
