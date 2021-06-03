package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service

import org.slf4j.event.Level
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.AdvertHouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.logging.logger
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.*

class AdvertHouseService(private val crud: AdvertHouseCrud) {

  private val logger = logger(this::class.java)

  suspend fun list(context: BeContext, query: RequestAdvertHouseList?): ResponseAdvertHouseList = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("advert-house-list-request-got")
    )
    crud.list(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("advert-house-list-request-handled")
    )
    return respondAdvertHouseList()
  }

  suspend fun create(context: BeContext, query: RequestAdvertHouseCreate?): ResponseAdvertHouseCreate = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("advert-house-create-request-got")
    )
    crud.create(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("advert-house-create-request-handled")
    )
    return respondAdvertHouseCreate()
  }

  suspend fun read(context: BeContext, query: RequestAdvertHouseRead?): ResponseAdvertHouseRead = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("advert-house-read-request-got")
    )
    crud.read(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("advert-house-read-request-handled")
    )
    return respondAdvertHouseRead()
  }

  suspend fun update(context: BeContext, query: RequestAdvertHouseUpdate?): ResponseAdvertHouseUpdate = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("advert-house-update-request-got")
    )
    crud.update(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("advert-house-update-request-handled")
    )
    return respondAdvertHouseUpdate()
  }

  suspend fun delete(context: BeContext, query: RequestAdvertHouseDelete?): ResponseAdvertHouseDelete = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("advert-house-delete-request-got")
    )
    crud.delete(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("advert-house-delete-request-handled")
    )
    return respondAdvertHouseDelete()
  }

}
