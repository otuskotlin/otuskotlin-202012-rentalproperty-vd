package ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.fu.controller

import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.HouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.*

class HouseController(val crud: HouseCrud) {
  fun list(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestHouseList? ->
    query?.also { setQuery(it) }
    crud.list(this)
    respondHouseList()
  }

  fun create(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestHouseCreate? ->
    query?.also { setQuery(it) }
    crud.create(this)
    respondHouseCreate()
  }

  fun read(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestHouseRead? ->
    query?.also { setQuery(it) }
    crud.read(this)
    respondHouseRead()
  }

  fun update(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestHouseUpdate? ->
    query?.also { setQuery(it) }
    crud.update(this)
    respondHouseUpdate()
  }

  fun delete(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestHouseDelete? ->
    query?.also { setQuery(it) }
    crud.delete(this)
    respondHouseDelete()
  }
}
