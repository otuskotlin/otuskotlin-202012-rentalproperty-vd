package ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.fu.controller

import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.AdvertFlatCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.flat.*

class AdvertFlatController(val crud: AdvertFlatCrud) {
  fun list(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestAdvertFlatList? ->
    query?.also { setQuery(it) }
    crud.list(this)
    respondAdvertFlatList()
  }

  fun create(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestAdvertFlatCreate? ->
    query?.also { setQuery(it) }
    crud.create(this)
    respondAdvertFlatCreate()
  }

  fun read(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestAdvertFlatRead? ->
    query?.also { setQuery(it) }
    crud.read(this)
    respondAdvertFlatRead()
  }

  fun update(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestAdvertFlatUpdate? ->
    query?.also { setQuery(it) }
    crud.update(this)
    respondAdvertFlatUpdate()
  }

  fun delete(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestAdvertFlatDelete? ->
    query?.also { setQuery(it) }
    crud.delete(this)
    respondAdvertFlatDelete()
  }
}
