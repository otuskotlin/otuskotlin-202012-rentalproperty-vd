package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.AdvertHouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.*

class AdvertHouseService(private val crud: AdvertHouseCrud) {

  suspend fun list(context: BeContext, query: RequestAdvertHouseList?): ResponseAdvertHouseList = with(context) {
    query?.also { setQuery(it) }
    crud.list(this)
    return respondAdvertHouseList()
  }

  suspend fun create(context: BeContext, query: RequestAdvertHouseCreate?): ResponseAdvertHouseCreate = with(context) {
    query?.also { setQuery(it) }
    crud.create(this)
    return respondAdvertHouseCreate()
  }

  suspend fun read(context: BeContext, query: RequestAdvertHouseRead?): ResponseAdvertHouseRead = with(context) {
    query?.also { setQuery(it) }
    crud.read(this)
    return respondAdvertHouseRead()
  }

  suspend fun update(context: BeContext, query: RequestAdvertHouseUpdate?): ResponseAdvertHouseUpdate = with(context) {
    query?.also { setQuery(it) }
    crud.update(this)
    return respondAdvertHouseUpdate()
  }

  suspend fun delete(context: BeContext, query: RequestAdvertHouseDelete?): ResponseAdvertHouseDelete = with(context) {
    query?.also { setQuery(it) }
    crud.delete(this)
    return respondAdvertHouseDelete()
  }

}
