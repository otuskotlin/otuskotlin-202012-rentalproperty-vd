package ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.fu.controller

import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.AdvertHouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.respondAdvertHouseList
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.setQuery
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.*

class AdvertHouseController(val crud: AdvertHouseCrud) {
  fun list(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestAdvertHouseList? ->
    query?.also { setQuery(it) }
    crud.list(this)
    respondAdvertHouseList()
  }

  fun create(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestAdvertHouseCreate? ->
    query?.also { setQuery(it) }
    crud.create(this)
    respondAdvertHouseList()
  }

  fun read(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestAdvertHouseRead? ->
    query?.also { setQuery(it) }
    crud.read(this)
    respondAdvertHouseList()
  }

  fun update(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestAdvertHouseUpdate? ->
    query?.also { setQuery(it) }
    crud.update(this)
    respondAdvertHouseList()
  }

  fun delete(request: ServerRequest): ServerResponse = handleRoute(request) { query: RequestAdvertHouseDelete? ->
    query?.also { setQuery(it) }
    crud.delete(this)
    respondAdvertHouseList()
  }
}
