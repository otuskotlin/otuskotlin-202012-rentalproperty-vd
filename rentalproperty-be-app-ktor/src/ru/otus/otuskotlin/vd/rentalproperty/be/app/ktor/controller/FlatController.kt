package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.config.AuthConfig
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helper.handleRoute
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service.FlatService
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.*

fun Routing.flatRouting(service: FlatService, authConfig: AuthConfig) {
  authenticate(authConfig.name, optional = authConfig.optional) {
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
      println(call.authentication.principal)  //for debug
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
}

