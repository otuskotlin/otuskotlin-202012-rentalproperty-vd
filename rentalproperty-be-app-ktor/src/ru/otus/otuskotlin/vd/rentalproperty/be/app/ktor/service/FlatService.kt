package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service

import org.slf4j.event.Level
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.FlatCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.logging.logger
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.*

class FlatService(private val crud: FlatCrud) {

  private val logger = logger(this::class.java)

  suspend fun list(context: BeContext, query: RequestFlatList?): ResponseFlatList = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("flat-list-request-got")
    )
    crud.list(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("flat-list-request-handled")
    )
    return respondFlatList()
  }

  suspend fun create(context: BeContext, query: RequestFlatCreate?): ResponseFlatCreate = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("flat-create-request-got")
    )
    crud.create(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("flat-create-request-handled")
    )
    return respondFlatCreate()
  }

  suspend fun read(context: BeContext, query: RequestFlatRead?): ResponseFlatRead = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("flat-read-request-got")
    )
    crud.read(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("flat-read-request-handled")
    )
    return respondFlatRead()
  }

  suspend fun update(context: BeContext, query: RequestFlatUpdate?): ResponseFlatUpdate = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("flat-update-request-got")
    )
    crud.update(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("flat-update-request-handled")
    )
    return respondFlatUpdate()
  }

  suspend fun delete(context: BeContext, query: RequestFlatDelete?): ResponseFlatDelete = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("flat-delete-request-got")
    )
    crud.delete(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("flat-delete-request-handled")
    )
    return respondFlatDelete()
  }

}
