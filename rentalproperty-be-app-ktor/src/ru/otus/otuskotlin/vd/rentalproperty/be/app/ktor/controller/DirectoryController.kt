package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helper.handleRoute
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service.DirectoryService
import ru.otus.otuskotlin.vd.rentalproperty.be.logging.mpLogger
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.*

private val logger = mpLogger(Routing::flatRouting::class.java)

fun Routing.directoryRouting(service: DirectoryService) {
  post(RestEndpoints.directoryCreate) {
    handleRoute<RequestDirectoryItemCreate, ResponseDirectoryItemCreate>("directory-create", logger) { query ->
      service.create(this, query)
    }
  }
  post(RestEndpoints.directoryRead) {
    handleRoute<RequestDirectoryItemRead, ResponseDirectoryItemRead>("directory-read", logger) { query ->
      service.read(this, query)
    }
  }
  post(RestEndpoints.directoryUpdate) {
    handleRoute<RequestDirectoryItemUpdate, ResponseDirectoryItemUpdate>("directory-update", logger) { query ->
      service.update(this, query)
    }
  }
  post(RestEndpoints.directoryDelete) {
    handleRoute<RequestDirectoryItemDelete, ResponseDirectoryItemDelete>("directory-delete", logger) { query ->
      service.delete(this, query)
    }
  }
  post(RestEndpoints.directoryList) {
    handleRoute<RequestDirectoryItemList, ResponseDirectoryItemList>("directory-list", logger) { query ->
      service.list(this, query)
    }
  }
}

