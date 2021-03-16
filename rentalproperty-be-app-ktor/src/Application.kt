package com.example

import com.example.service.HouseService
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ResponseStatusDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

  val houseService = HouseService()

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

    route("/houses") {
      post("/get") {
        try {
          val query = call.receive<Message>() as RequestHouseRead
          call.respond(houseService.get(query))
        } catch (e: Throwable) {
          call.respond(
            ResponseHouseRead(
              status = ResponseStatusDto.BAD_REQUEST
            )
          )
        }
      }
      post("/create") {
        try {
          val query = call.receive<Message>() as RequestHouseCreate
          call.respond(houseService.create(query))
        } catch (e: Throwable) {
          call.respond(
            ResponseHouseCreate(
              status = ResponseStatusDto.BAD_REQUEST
            )
          )
        }
      }
      post("/update") {
        try {
          val query = call.receive<Message>() as RequestHouseUpdate
          call.respond(houseService.update(query))
        } catch (e: Throwable) {
          call.respond(
            ResponseHouseUpdate(
              status = ResponseStatusDto.BAD_REQUEST
            )
          )
        }
      }
      post("/delete") {
        try {
          val query = call.receive<Message>() as RequestHouseDelete
          call.respond(houseService.delete(query))
        } catch (e: Throwable) {
          call.respond(
            ResponseHouseDelete(
              status = ResponseStatusDto.BAD_REQUEST
            )
          )
        }
      }
      post("/filter") {
        try {
          val query = call.receive<Message>() as RequestHouseList
          call.respond(houseService.filter(query))
        } catch (e: Throwable) {
          call.respond(
            ResponseHouseList(
              status = ResponseStatusDto.BAD_REQUEST
            )
          )
        }
      }
    }
  }
}

