package ru.otus.otuskotlin.vd.rentalproperty.ktor.controller

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.ktor.service.AdvertRentHouseService
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.advert.house.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ResponseStatusDto

fun Routing.advertHouseRoute() {

  val advertHouseService = AdvertRentHouseService()

  route("/adverts/houses") {
    post("/get") {
      try {
        val query = call.receive<Message>() as RequestAdvertRentHouseRead
        call.respond(advertHouseService.get(query))
      } catch (e: Throwable) {
        call.respond(
          ResponseAdvertRentHouseRead(
            status = ResponseStatusDto.BAD_REQUEST
          )
        )
      }
    }
    post("/create") {
      try {
        val query = call.receive<Message>() as RequestAdvertRentHouseCreate
        call.respond(advertHouseService.create(query))
      } catch (e: Throwable) {
        call.respond(
          ResponseAdvertRentHouseCreate(
            status = ResponseStatusDto.BAD_REQUEST
          )
        )
      }
    }
    post("/update") {
      try {
        val query = call.receive<Message>() as RequestAdvertRentHouseUpdate
        call.respond(advertHouseService.update(query))
      } catch (e: Throwable) {
        call.respond(
          ResponseAdvertRentHouseUpdate(
            status = ResponseStatusDto.BAD_REQUEST
          )
        )
      }
    }
    post("/delete") {
      try {
        val query = call.receive<Message>() as RequestAdvertRentHouseDelete
        call.respond(advertHouseService.delete(query))
      } catch (e: Throwable) {
        call.respond(
          ResponseAdvertRentHouseDelete(
            status = ResponseStatusDto.BAD_REQUEST
          )
        )
      }
    }
    post("/filter") {
      try {
        val query = call.receive<Message>() as RequestAdvertRentHouseList
        call.respond(advertHouseService.filter(query))
      } catch (e: Throwable) {
        call.respond(
          ResponseAdvertRentHouseList(
            status = ResponseStatusDto.BAD_REQUEST
          )
        )
      }
    }
  }
}

