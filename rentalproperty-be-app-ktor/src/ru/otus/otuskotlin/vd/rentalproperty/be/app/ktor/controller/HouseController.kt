package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helper.handleRoute
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service.HouseService
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.*

fun Routing.houseRouting(service: HouseService) {
  post(RestEndpoints.houseCreate) {
    handleRoute<RequestHouseCreate, ResponseHouseCreate> { query ->
      service.create(this, query)
    }
  }
  post(RestEndpoints.houseRead) {
    handleRoute<RequestHouseRead, ResponseHouseRead> { query ->
      service.read(this, query)
    }
  }
  post(RestEndpoints.houseUpdate) {
    handleRoute<RequestHouseUpdate, ResponseHouseUpdate> { query ->
      service.update(this, query)
    }
  }
  post(RestEndpoints.houseDelete) {
    handleRoute<RequestHouseDelete, ResponseHouseDelete> { query ->
      service.delete(this, query)
    }
  }
  post(RestEndpoints.houseList) {
    handleRoute<RequestHouseList, ResponseHouseList> { query ->
      service.list(this, query)
    }
  }
}

