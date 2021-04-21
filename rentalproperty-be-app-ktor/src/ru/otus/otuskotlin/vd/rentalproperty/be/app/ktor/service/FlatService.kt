package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.FlatCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.*

class FlatService(private val crud: FlatCrud) {

  suspend fun list(context: BeContext, query: RequestFlatList?): ResponseFlatList = with(context) {
    query?.also { setQuery(it) }
    crud.list(this)
    return respondFlatList()
  }

  suspend fun create(context: BeContext, query: RequestFlatCreate?): ResponseFlatCreate = with(context) {
    query?.also { setQuery(it) }
    crud.create(this)
    return respondFlatCreate()
  }

  suspend fun read(context: BeContext, query: RequestFlatRead?): ResponseFlatRead = with(context) {
    query?.also { setQuery(it) }
    crud.read(this)
    return respondFlatRead()
  }

  suspend fun update(context: BeContext, query: RequestFlatUpdate?): ResponseFlatUpdate = with(context) {
    query?.also { setQuery(it) }
    crud.update(this)
    return respondFlatUpdate()
  }

  suspend fun delete(context: BeContext, query: RequestFlatDelete?): ResponseFlatDelete = with(context) {
    query?.also { setQuery(it) }
    crud.delete(this)
    return respondFlatDelete()
  }

}
