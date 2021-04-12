package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helpers.handleRoute
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.FlatService
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.*

fun Routing.flatRouting(service: FlatService) {
  post(RestEndpoints.flatCreate) {
    handleRoute<RequestFlatCreate, ResponseFlatCreate> { query ->
      service.create(this, query)
    }
  }
  post(RestEndpoints.flatRead) {
    handleRoute<RequestFlatRead, ResponseFlatRead> { query ->
      service.read(this, query)
    }
  }
  post(RestEndpoints.flatUpdate) {
    handleRoute<RequestFlatUpdate, ResponseFlatUpdate> { query ->
      service.update(this, query)
    }
  }
  post(RestEndpoints.flatDelete) {
    handleRoute<RequestFlatDelete, ResponseFlatDelete> { query ->
      service.delete(this, query)
    }
  }
  post(RestEndpoints.flatList) {
    handleRoute<RequestFlatList, ResponseFlatList> { query ->
      service.list(this, query)
    }
  }
}

