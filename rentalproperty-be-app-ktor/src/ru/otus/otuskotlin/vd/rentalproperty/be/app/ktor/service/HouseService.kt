package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service

import org.slf4j.event.Level
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.HouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.logging.logger
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.*

class HouseService(private val crud: HouseCrud) {

  private val logger = logger(this::class.java)

  suspend fun list(context: BeContext, query: RequestHouseList?): ResponseHouseList = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("house-list-request-got")
    )
    crud.list(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("house-list-request-handled")
    )
    return respondHouseList()
  }

  suspend fun create(context: BeContext, query: RequestHouseCreate?): ResponseHouseCreate = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("house-create-request-got")
    )
    crud.create(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("house-create-request-handled")
    )
    return respondHouseCreate()
  }

  suspend fun read(context: BeContext, query: RequestHouseRead?): ResponseHouseRead = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("house-read-request-got")
    )
    crud.read(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("house-read-request-handled")
    )
    return respondHouseRead()
  }

  suspend fun update(context: BeContext, query: RequestHouseUpdate?): ResponseHouseUpdate = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("house-update-request-got")
    )
    crud.update(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("house-update-request-handled")
    )
    return respondHouseUpdate()
  }

  suspend fun delete(context: BeContext, query: RequestHouseDelete?): ResponseHouseDelete = with(context) {
    query?.also { setQuery(it) }
    logger.log(
      msg = "Request got, query = {}",
      level = Level.INFO,
      data = toLog("house-delete-request-got")
    )
    crud.delete(this)
    logger.log(
      msg = "Response ready, response = {}",
      level = Level.INFO,
      data = toLog("house-delete-request-handled")
    )
    return respondHouseDelete()
  }

}
