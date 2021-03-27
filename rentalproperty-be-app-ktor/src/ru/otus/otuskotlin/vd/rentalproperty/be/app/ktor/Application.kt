package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller.advertHouseRoute
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller.houseRoute
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.AdvertHouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.HouseCrud

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

  val houseCrud = HouseCrud()
  val advertHouseCrud = AdvertHouseCrud()

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

    houseRoute(houseCrud)
    advertHouseRoute(advertHouseCrud)
  }
}

