package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.HouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.*

class HouseService(private val crud: HouseCrud) {

  suspend fun list(context: BeContext, query: RequestHouseList?): ResponseHouseList = with(context) {
    query?.also { setQuery(it) }
    crud.list(this)
    return respondHouseList()
  }

  suspend fun create(context: BeContext, query: RequestHouseCreate?): ResponseHouseCreate = with(context) {
    query?.also { setQuery(it) }
    crud.create(this)
    return respondHouseCreate()
  }

  suspend fun read(context: BeContext, query: RequestHouseRead?): ResponseHouseRead = with(context) {
    query?.also { setQuery(it) }
    crud.read(this)
    return respondHouseRead()
  }

  suspend fun update(context: BeContext, query: RequestHouseUpdate?): ResponseHouseUpdate = with(context) {
    query?.also { setQuery(it) }
    crud.update(this)
    return respondHouseUpdate()
  }

  suspend fun delete(context: BeContext, query: RequestHouseDelete?): ResponseHouseDelete = with(context) {
    query?.also { setQuery(it) }
    crud.delete(this)
    return respondHouseDelete()
  }

}
