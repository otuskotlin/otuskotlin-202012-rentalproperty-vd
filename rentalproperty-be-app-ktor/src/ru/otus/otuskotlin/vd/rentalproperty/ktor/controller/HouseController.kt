package ru.otus.otuskotlin.vd.rentalproperty.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.business.logic.backend.HouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.common.kmp.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.realty.house.*

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

