package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helper.handleRoute
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service.AdvertFlatService
import ru.otus.otuskotlin.vd.rentalproperty.be.logging.logger
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.flat.*

private val logger = logger(Routing::flatRouting::class.java)

fun Routing.advertFlatRouting(service: AdvertFlatService) {
  post(RestEndpoints.advertFlatCreate) {
    handleRoute<RequestAdvertFlatCreate, ResponseAdvertFlatCreate>("advert-flat-create", logger) { query ->
      service.create(this, query)
    }
  }
  post(RestEndpoints.advertFlatRead) {
    handleRoute<RequestAdvertFlatRead, ResponseAdvertFlatRead>("advert-flat-read", logger) { query ->
      service.read(this, query)
    }
  }
  post(RestEndpoints.advertFlatUpdate) {
    handleRoute<RequestAdvertFlatUpdate, ResponseAdvertFlatUpdate>("advert-flat-update", logger) { query ->
      service.update(this, query)
    }
  }
  post(RestEndpoints.advertFlatDelete) {
    handleRoute<RequestAdvertFlatDelete, ResponseAdvertFlatDelete>("advert-flat-delete", logger) { query ->
      service.delete(this, query)
    }
  }
  post(RestEndpoints.advertFlatList) {
    handleRoute<RequestAdvertFlatList, ResponseAdvertFlatList>("advert-flat-list", logger) { query ->
      service.list(this, query)
    }
  }
}

