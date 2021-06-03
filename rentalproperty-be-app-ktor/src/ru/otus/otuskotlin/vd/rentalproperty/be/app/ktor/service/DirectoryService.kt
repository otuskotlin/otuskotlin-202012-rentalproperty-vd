package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service

import org.slf4j.event.Level
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.DirectoryCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.logging.logger
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.openapi.setQuery
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.*

class DirectoryService(private val crud: DirectoryCrud) {

  private val logger = logger(this::class.java)

  suspend fun list(context: BeContext, query: RequestDirectoryItemList?): ResponseDirectoryItemList =
    with(context) {
      query?.also { setQuery(it) }
      logger.log(
        msg = "Request got, query = {}",
        level = Level.INFO,
        data = toLog("directory-list-request-got")
      )
      crud.list(this)
      logger.log(
        msg = "Response ready, response = {}",
        level = Level.INFO,
        data = toLog("directory-list-request-handled")
      )
      return respondDirectoryItemList()
    }

  suspend fun create(context: BeContext, query: RequestDirectoryItemCreate?): ResponseDirectoryItemCreate =
    with(context) {
      query?.also { setQuery(it) }
      logger.log(
        msg = "Request got, query = {}",
        level = Level.INFO,
        data = toLog("directory-create-request-got")
      )
      crud.create(this)
      logger.log(
        msg = "Response ready, response = {}",
        level = Level.INFO,
        data = toLog("directory-create-request-handled")
      )
      return respondDirectoryItemCreate()
    }

  suspend fun read(context: BeContext, query: RequestDirectoryItemRead?): ResponseDirectoryItemRead =
    with(context) {
      query?.also { setQuery(it) }
      logger.log(
        msg = "Request got, query = {}",
        level = Level.INFO,
        data = toLog("directory-read-request-got")
      )
      crud.read(this)
      logger.log(
        msg = "Response ready, response = {}",
        level = Level.INFO,
        data = toLog("directory-read-request-handled")
      )
      return respondDirectoryItemRead()
    }

  suspend fun update(context: BeContext, query: RequestDirectoryItemUpdate?): ResponseDirectoryItemUpdate =
    with(context) {
      query?.also { setQuery(it) }
      logger.log(
        msg = "Request got, query = {}",
        level = Level.INFO,
        data = toLog("directory-update-request-got")
      )
      crud.update(this)
      logger.log(
        msg = "Response ready, response = {}",
        level = Level.INFO,
        data = toLog("directory-update-request-handled")
      )
      return respondDirectoryItemUpdate()
    }

  suspend fun delete(context: BeContext, query: RequestDirectoryItemDelete?): ResponseDirectoryItemDelete =
    with(context) {
      query?.also { setQuery(it) }
      logger.log(
        msg = "Request got, query = {}",
        level = Level.INFO,
        data = toLog("directory-delete-request-got")
      )
      crud.delete(this)
      logger.log(
        msg = "Response ready, response = {}",
        level = Level.INFO,
        data = toLog("directory-delete-request-handled")
      )
      return respondDirectoryItemDelete()
    }
}
