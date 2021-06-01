package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helper.handleRoute
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service.HouseService
import ru.otus.otuskotlin.vd.rentalproperty.be.logging.mpLogger
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.*

private val logger = mpLogger(Routing::flatRouting::class.java)

fun Routing.houseRouting(service: HouseService) {
  post(RestEndpoints.houseCreate) {
    handleRoute<RequestHouseCreate, ResponseHouseCreate>("house-create", logger) { query ->
      service.create(this, query)
    }
  }
  post(RestEndpoints.houseRead) {
    handleRoute<RequestHouseRead, ResponseHouseRead>("house-read", logger) { query ->
      service.read(this, query)
    }
  }
  post(RestEndpoints.houseUpdate) {
    handleRoute<RequestHouseUpdate, ResponseHouseUpdate>("house-update", logger) { query ->
      service.update(this, query)
    }
  }
  post(RestEndpoints.houseDelete) {
    handleRoute<RequestHouseDelete, ResponseHouseDelete>("house-delete", logger) { query ->
      service.delete(this, query)
    }
  }
  post(RestEndpoints.houseList) {
    handleRoute<RequestHouseList, ResponseHouseList>("house-list", logger) { query ->
      service.list(this, query)
    }
  }
}

