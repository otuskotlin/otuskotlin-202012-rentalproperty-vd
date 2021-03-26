package ru.otus.otuskotlin.vd.rentalproperty.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.business.logic.backend.AdvertHouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.common.kmp.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.advert.house.*

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

