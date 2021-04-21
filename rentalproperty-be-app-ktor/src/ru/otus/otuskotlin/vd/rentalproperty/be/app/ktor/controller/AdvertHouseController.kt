package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helpers.handleRoute
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.AdvertHouseService
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.*

fun Routing.advertHouseRouting(service: AdvertHouseService) {
  post(RestEndpoints.advertHouseCreate) {
    handleRoute<RequestAdvertHouseCreate, ResponseAdvertHouseCreate> { query ->
      service.create(this, query)
    }
  }
  post(RestEndpoints.advertHouseRead) {
    handleRoute<RequestAdvertHouseRead, ResponseAdvertHouseRead> { query ->
      service.read(this, query)
    }
  }
  post(RestEndpoints.advertHouseUpdate) {
    handleRoute<RequestAdvertHouseUpdate, ResponseAdvertHouseUpdate> { query ->
      service.update(this, query)
    }
  }
  post(RestEndpoints.advertHouseDelete) {
    handleRoute<RequestAdvertHouseDelete, ResponseAdvertHouseDelete> { query ->
      service.delete(this, query)
    }
  }
  post(RestEndpoints.advertHouseList) {
    handleRoute<RequestAdvertHouseList, ResponseAdvertHouseList> { query ->
      service.list(this, query)
    }
  }
}

