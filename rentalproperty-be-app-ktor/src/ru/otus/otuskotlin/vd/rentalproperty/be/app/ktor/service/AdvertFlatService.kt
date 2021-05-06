package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.AdvertFlatCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.flat.*

class AdvertFlatService(private val crud: AdvertFlatCrud) {

  suspend fun list(context: BeContext, query: RequestAdvertFlatList?): ResponseAdvertFlatList = with(context) {
    query?.also { setQuery(it) }
    crud.list(this)
    return respondAdvertFlatList()
  }

  suspend fun create(context: BeContext, query: RequestAdvertFlatCreate?): ResponseAdvertFlatCreate = with(context) {
    query?.also { setQuery(it) }
    crud.create(this)
    return respondAdvertFlatCreate()
  }

  suspend fun read(context: BeContext, query: RequestAdvertFlatRead?): ResponseAdvertFlatRead = with(context) {
    query?.also { setQuery(it) }
    crud.read(this)
    return respondAdvertFlatRead()
  }

  suspend fun update(context: BeContext, query: RequestAdvertFlatUpdate?): ResponseAdvertFlatUpdate = with(context) {
    query?.also { setQuery(it) }
    crud.update(this)
    return respondAdvertFlatUpdate()
  }

  suspend fun delete(context: BeContext, query: RequestAdvertFlatDelete?): ResponseAdvertFlatDelete = with(context) {
    query?.also { setQuery(it) }
    crud.delete(this)
    return respondAdvertFlatDelete()
  }

}
