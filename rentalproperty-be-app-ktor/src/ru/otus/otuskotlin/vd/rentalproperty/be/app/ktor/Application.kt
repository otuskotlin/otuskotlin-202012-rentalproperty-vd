package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.websocket.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.AdvertFlatService
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.AdvertHouseService
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.FlatService
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.HouseService
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.AdvertFlatCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.AdvertHouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.FlatCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.HouseCrud

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

  val houseCrud = HouseCrud()
  val flatCrud = FlatCrud()
  val advertFlatCrud = AdvertFlatCrud()
  val advertHouseCrud = AdvertHouseCrud()

  val houseService = HouseService(houseCrud)
  val flatService = FlatService(flatCrud)
  val advertHouseService = AdvertHouseService(advertHouseCrud)
  val advertFlatService = AdvertFlatService(advertFlatCrud)

  install(CORS) {
    method(HttpMethod.Options)
    method(HttpMethod.Put)
    method(HttpMethod.Delete)
    method(HttpMethod.Patch)
    header(HttpHeaders.Authorization)
    header("MyCustomHeader")
    allowCredentials = true
    anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
  }

  install(WebSockets)
  install(ContentNegotiation) {
    json(
      contentType = ContentType.Application.Json,
      json = jsonConfig,
    )
  }

  routing {
    get("/") {
      call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
    }

    // Static feature. Try to access `/static/ktor_logo.svg`
    static("/static") {
      resources("static")
    }

    houseRouting(houseService)
    flatRouting(flatService)
    advertFlatRouting(advertFlatService)
    advertHouseRouting(advertHouseService)

    mpWebsocket(houseService, flatService, advertHouseService, advertFlatService)
  }
}

