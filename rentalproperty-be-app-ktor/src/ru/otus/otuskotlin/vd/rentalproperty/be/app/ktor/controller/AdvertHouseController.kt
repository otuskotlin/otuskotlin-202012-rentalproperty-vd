package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.AdvertHouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.*

fun Routing.advertHouseRoute(crud: AdvertHouseCrud) {
  post(RestEndpoints.houseCreate) {
    handleRoute<RequestAdvertHouseCreate, ResponseAdvertHouseCreate> { query ->
      query?.also { setQuery(it) }
      crud.create(this)
      respondAdvertHouseCreate()
    }
  }
  post(RestEndpoints.houseRead) {
    handleRoute<RequestAdvertHouseRead, ResponseAdvertHouseRead> { query ->
      query?.also { setQuery(it) }
      crud.read(this)
      respondAdvertHouseRead()
    }
  }
  post(RestEndpoints.houseUpdate) {
    handleRoute<RequestAdvertHouseUpdate, ResponseAdvertHouseUpdate> { query ->
      query?.also { setQuery(it) }
      crud.read(this)
      respondAdvertHouseUpdate()
    }
  }
  post(RestEndpoints.houseDelete) {
    handleRoute<RequestAdvertHouseDelete, ResponseAdvertHouseDelete> { query ->
      query?.also { setQuery(it) }
      crud.read(this)
      respondAdvertHouseDelete()
    }
  }
  post(RestEndpoints.houseList) {
    handleRoute<RequestAdvertHouseList, ResponseAdvertHouseList> { query ->
      query?.also { setQuery(it) }
      crud.read(this)
      respondAdvertHouseList()
    }
  }
}

