package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.HouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.*

fun Routing.houseRoute(crud: HouseCrud) {
  post(RestEndpoints.houseCreate) {
    handleRoute<RequestHouseCreate, ResponseHouseCreate> { query ->
      query?.also { setQuery(it) }
      crud.create(this)
      respondHouseCreate()
    }
  }
  post(RestEndpoints.houseRead) {
    handleRoute<RequestHouseRead, ResponseHouseRead> { query ->
      query?.also { setQuery(it) }
      crud.read(this)
      respondHouseRead()
    }
  }
  post(RestEndpoints.houseUpdate) {
    handleRoute<RequestHouseUpdate, ResponseHouseUpdate> { query ->
      query?.also { setQuery(it) }
      crud.read(this)
      respondHouseUpdate()
    }
  }
  post(RestEndpoints.houseDelete) {
    handleRoute<RequestHouseDelete, ResponseHouseDelete> { query ->
      query?.also { setQuery(it) }
      crud.read(this)
      respondHouseDelete()
    }
  }
  post(RestEndpoints.houseList) {
    handleRoute<RequestHouseList, ResponseHouseList> { query ->
      query?.also { setQuery(it) }
      crud.read(this)
      respondHouseList()
    }
  }
}

