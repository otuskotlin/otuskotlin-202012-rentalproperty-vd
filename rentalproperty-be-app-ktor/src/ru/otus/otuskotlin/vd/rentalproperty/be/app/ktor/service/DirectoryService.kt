package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.DirectoryCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.openapi.setQuery
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.*

class DirectoryService(private val crud: DirectoryCrud) {
  suspend fun list(context: BeContext, query: RequestDirectoryItemList?): ResponseDirectoryItemList =
    with(context) {
      query?.also { setQuery(it) }
      crud.list(this)
      return respondDirectoryItemList()
    }

  suspend fun create(context: BeContext, query: RequestDirectoryItemCreate?): ResponseDirectoryItemCreate =
    with(context) {
      query?.also { setQuery(it) }
      crud.create(this)
      return respondDirectoryItemCreate()
    }

  suspend fun read(context: BeContext, query: RequestDirectoryItemRead?): ResponseDirectoryItemRead =
    with(context) {
      query?.also { setQuery(it) }
      crud.read(this)
      return respondDirectoryItemRead()
    }

  suspend fun update(context: BeContext, query: RequestDirectoryItemUpdate?): ResponseDirectoryItemUpdate =
    with(context) {
      query?.also { setQuery(it) }
      crud.update(this)
      return respondDirectoryItemUpdate()
    }

  suspend fun delete(context: BeContext, query: RequestDirectoryItemDelete?): ResponseDirectoryItemDelete =
    with(context) {
      query?.also { setQuery(it) }
      crud.delete(this)
      return respondDirectoryItemDelete()
    }
}
