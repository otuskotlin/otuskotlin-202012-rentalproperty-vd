package ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.fu.controller

import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.FlatCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.*

class FlatController(val crud: FlatCrud) {
  fun list(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestFlatList? ->
    query?.also { setQuery(it) }
    crud.list(this)
    respondFlatList()
  }

  fun create(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestFlatCreate? ->
    query?.also { setQuery(it) }
    crud.create(this)
    respondFlatCreate()
  }

  fun read(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestFlatRead? ->
    query?.also { setQuery(it) }
    crud.read(this)
    respondFlatRead()
  }

  fun update(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestFlatUpdate? ->
    query?.also { setQuery(it) }
    crud.update(this)
    respondFlatUpdate()
  }

  fun delete(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestFlatDelete? ->
    query?.also { setQuery(it) }
    crud.delete(this)
    respondFlatDelete()
  }
}
