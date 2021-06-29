package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.config.AuthProperties
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helper.handleRoute
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service.FlatService
import ru.otus.otuskotlin.vd.rentalproperty.be.logging.logger
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.*

private val logger = logger(Routing::flatRouting::class.java)

fun Routing.flatRouting(service: FlatService, authProperties: AuthProperties) {
  authenticate(authProperties.name, optional = authProperties.optional) {
    post(RestEndpoints.flatCreate) {
      handleRoute<RequestFlatCreate, ResponseFlatCreate>("flat-create", logger) { query ->
        useAuth = !authProperties.authOff
        service.create(this, query)
      }
    }
    post(RestEndpoints.flatRead) {
      handleRoute<RequestFlatRead, ResponseFlatRead>("flat-read", logger) { query ->
        useAuth = !authProperties.authOff
        service.read(this, query)
      }
    }
    post(RestEndpoints.flatUpdate) {
      println(call.authentication.principal)  //for debug
      handleRoute<RequestFlatUpdate, ResponseFlatUpdate>("flat-update", logger) { query ->
        useAuth = !authProperties.authOff
        service.update(this, query)
      }
    }
    post(RestEndpoints.flatDelete) {
      handleRoute<RequestFlatDelete, ResponseFlatDelete>("flat-delete", logger) { query ->
        useAuth = !authProperties.authOff
        service.delete(this, query)
      }
    }
    post(RestEndpoints.flatList) {
      handleRoute<RequestFlatList, ResponseFlatList>("flat-list", logger) { query ->
        useAuth = !authProperties.authOff
        service.list(this, query)
      }
    }
  }
}

