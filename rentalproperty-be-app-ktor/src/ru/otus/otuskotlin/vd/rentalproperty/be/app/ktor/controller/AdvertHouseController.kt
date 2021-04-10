package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.AdvertHouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.*

fun Routing.advertHouseRoute(crud: AdvertHouseCrud) {
  post(RestEndpoints.advertHouseCreate) {
    handleRoute<RequestAdvertHouseCreate, ResponseAdvertHouseCreate> { query ->
      query?.also { setQuery(it) }
      crud.create(this)
      respondAdvertHouseCreate()
    }
  }
  post(RestEndpoints.advertHouseRead) {
    handleRoute<RequestAdvertHouseRead, ResponseAdvertHouseRead> { query ->
      query?.also { setQuery(it) }
      crud.read(this)
      respondAdvertHouseRead()
    }
  }
  post(RestEndpoints.advertHouseUpdate) {
    handleRoute<RequestAdvertHouseUpdate, ResponseAdvertHouseUpdate> { query ->
      query?.also { setQuery(it) }
      crud.update(this)
      respondAdvertHouseUpdate()
    }
  }
  post(RestEndpoints.advertHouseDelete) {
    handleRoute<RequestAdvertHouseDelete, ResponseAdvertHouseDelete> { query ->
      query?.also { setQuery(it) }
      crud.delete(this)
      respondAdvertHouseDelete()
    }
  }
  post(RestEndpoints.advertHouseList) {
    handleRoute<RequestAdvertHouseList, ResponseAdvertHouseList> { query ->
      query?.also { setQuery(it) }
      crud.list(this)
      respondAdvertHouseList()
    }
  }
}

