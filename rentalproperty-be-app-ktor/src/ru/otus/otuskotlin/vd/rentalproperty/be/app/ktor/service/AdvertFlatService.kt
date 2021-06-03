package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service

import org.slf4j.event.Level
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.AdvertFlatCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.logging.logger
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.flat.*

class AdvertFlatService(private val crud: AdvertFlatCrud) {

  private val logger = logger(this::class.java)

  suspend fun list(context: BeContext, query: RequestAdvertFlatList?): ResponseAdvertFlatList = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("advert-flat-list-request-got")
    )
    crud.list(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("advert-flat-list-request-handled")
    )
    return respondAdvertFlatList()
  }

  suspend fun create(context: BeContext, query: RequestAdvertFlatCreate?): ResponseAdvertFlatCreate = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("advert-flat-create-request-got")
    )
    crud.create(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("advert-flat-create-request-handled")
    )
    return respondAdvertFlatCreate()
  }

  suspend fun read(context: BeContext, query: RequestAdvertFlatRead?): ResponseAdvertFlatRead = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("advert-flat-read-request-got")
    )
    crud.read(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("advert-flat-read-request-handled")
    )
    return respondAdvertFlatRead()
  }

  suspend fun update(context: BeContext, query: RequestAdvertFlatUpdate?): ResponseAdvertFlatUpdate = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("advert-flat-update-request-got")
    )
    crud.update(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("advert-flat-update-request-handled")
    )
    return respondAdvertFlatUpdate()
  }

  suspend fun delete(context: BeContext, query: RequestAdvertFlatDelete?): ResponseAdvertFlatDelete = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("advert-flat-delete-request-got")
    )
    crud.delete(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("advert-flat-delete-request-handled")
    )
    return respondAdvertFlatDelete()
  }

}
