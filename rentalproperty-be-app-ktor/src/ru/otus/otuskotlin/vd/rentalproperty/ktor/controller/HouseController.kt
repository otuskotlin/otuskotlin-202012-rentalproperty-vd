package ru.otus.otuskotlin.vd.rentalproperty.ktor.controller

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.ktor.service.HouseService
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ResponseStatusDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.realty.house.*

fun Routing.houseRoute() {

  val houseService = HouseService()

  route("/realty/houses") {
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

