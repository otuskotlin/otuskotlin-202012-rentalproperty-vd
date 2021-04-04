package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.AdvertFlatCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.flat.*

fun Routing.advertFlatRoute(crud: AdvertFlatCrud) {
  post(RestEndpoints.advertFlatCreate) {
    handleRoute<RequestAdvertFlatCreate, ResponseAdvertFlatCreate> { query ->
      query?.also { setQuery(it) }
      crud.create(this)
      respondAdvertFlatCreate()
    }
  }
  post(RestEndpoints.advertFlatRead) {
    handleRoute<RequestAdvertFlatRead, ResponseAdvertFlatRead> { query ->
      query?.also { setQuery(it) }
      crud.read(this)
      respondAdvertFlatRead()
    }
  }
  post(RestEndpoints.advertFlatUpdate) {
    handleRoute<RequestAdvertFlatUpdate, ResponseAdvertFlatUpdate> { query ->
      query?.also { setQuery(it) }
      crud.update(this)
      respondAdvertFlatUpdate()
    }
  }
  post(RestEndpoints.advertFlatDelete) {
    handleRoute<RequestAdvertFlatDelete, ResponseAdvertFlatDelete> { query ->
      query?.also { setQuery(it) }
      crud.delete(this)
      respondAdvertFlatDelete()
    }
  }
  post(RestEndpoints.advertFlatList) {
    handleRoute<RequestAdvertFlatList, ResponseAdvertFlatList> { query ->
      query?.also { setQuery(it) }
      crud.list(this)
      respondAdvertFlatList()
    }
  }
}

