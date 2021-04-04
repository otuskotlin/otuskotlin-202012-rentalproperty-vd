package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.FlatCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.*

fun Routing.flatRoute(crud: FlatCrud) {
  post(RestEndpoints.flatCreate) {
    handleRoute<RequestFlatCreate, ResponseFlatCreate> { query ->
      query?.also { setQuery(it) }
      crud.create(this)
      respondFlatCreate()
    }
  }
  post(RestEndpoints.flatRead) {
    handleRoute<RequestFlatRead, ResponseFlatRead> { query ->
      query?.also { setQuery(it) }
      crud.read(this)
      respondFlatRead()
    }
  }
  post(RestEndpoints.flatUpdate) {
    handleRoute<RequestFlatUpdate, ResponseFlatUpdate> { query ->
      query?.also { setQuery(it) }
      crud.update(this)
      respondFlatUpdate()
    }
  }
  post(RestEndpoints.flatDelete) {
    handleRoute<RequestFlatDelete, ResponseFlatDelete> { query ->
      query?.also { setQuery(it) }
      crud.delete(this)
      respondFlatDelete()
    }
  }
  post(RestEndpoints.flatList) {
    handleRoute<RequestFlatList, ResponseFlatList> { query ->
      query?.also { setQuery(it) }
      crud.list(this)
      respondFlatList()
    }
  }
}

