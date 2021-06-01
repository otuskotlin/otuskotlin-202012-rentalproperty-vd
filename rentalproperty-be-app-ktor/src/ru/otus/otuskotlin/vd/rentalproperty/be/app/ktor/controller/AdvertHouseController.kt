package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helper.handleRoute
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service.AdvertHouseService
import ru.otus.otuskotlin.vd.rentalproperty.be.logging.mpLogger
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.*

private val logger = mpLogger(Routing::flatRouting::class.java)

fun Routing.advertHouseRouting(service: AdvertHouseService) {
  post(RestEndpoints.advertHouseCreate) {
    handleRoute<RequestAdvertHouseCreate, ResponseAdvertHouseCreate>("advert-house-create", logger) { query ->
      service.create(this, query)
    }
  }
  post(RestEndpoints.advertHouseRead) {
    handleRoute<RequestAdvertHouseRead, ResponseAdvertHouseRead>("advert-house-read", logger) { query ->
      service.read(this, query)
    }
  }
  post(RestEndpoints.advertHouseUpdate) {
    handleRoute<RequestAdvertHouseUpdate, ResponseAdvertHouseUpdate>("advert-house-update", logger) { query ->
      service.update(this, query)
    }
  }
  post(RestEndpoints.advertHouseDelete) {
    handleRoute<RequestAdvertHouseDelete, ResponseAdvertHouseDelete>("advert-house-delete", logger) { query ->
      service.delete(this, query)
    }
  }
  post(RestEndpoints.advertHouseList) {
    handleRoute<RequestAdvertHouseList, ResponseAdvertHouseList>("advert-house-list", logger) { query ->
      service.list(this, query)
    }
  }
}

