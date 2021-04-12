package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helpers.handleRoute
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.AdvertFlatService
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.flat.*

fun Routing.advertFlatRouting(service: AdvertFlatService) {
  post(RestEndpoints.advertFlatCreate) {
    handleRoute<RequestAdvertFlatCreate, ResponseAdvertFlatCreate> { query ->
      service.create(this, query)
    }
  }
  post(RestEndpoints.advertFlatRead) {
    handleRoute<RequestAdvertFlatRead, ResponseAdvertFlatRead> { query ->
      service.read(this, query)
    }
  }
  post(RestEndpoints.advertFlatUpdate) {
    handleRoute<RequestAdvertFlatUpdate, ResponseAdvertFlatUpdate> { query ->
      service.update(this, query)
    }
  }
  post(RestEndpoints.advertFlatDelete) {
    handleRoute<RequestAdvertFlatDelete, ResponseAdvertFlatDelete> { query ->
      service.delete(this, query)
    }
  }
  post(RestEndpoints.advertFlatList) {
    handleRoute<RequestAdvertFlatList, ResponseAdvertFlatList> { query ->
      service.list(this, query)
    }
  }
}

