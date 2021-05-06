package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helper.handleRoute
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service.DirectoryService
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.*

fun Routing.directoryRouting(service: DirectoryService) {
  post(RestEndpoints.directoryCreate) {
    handleRoute<RequestDirectoryItemCreate, ResponseDirectoryItemCreate> { query ->
      service.create(this, query)
    }
  }
  post(RestEndpoints.directoryRead) {
    handleRoute<RequestDirectoryItemRead, ResponseDirectoryItemRead> { query ->
      service.read(this, query)
    }
  }
  post(RestEndpoints.directoryUpdate) {
    handleRoute<RequestDirectoryItemUpdate, ResponseDirectoryItemUpdate> { query ->
      service.update(this, query)
    }
  }
  post(RestEndpoints.directoryDelete) {
    handleRoute<RequestDirectoryItemDelete, ResponseDirectoryItemDelete> { query ->
      service.delete(this, query)
    }
  }
  post(RestEndpoints.directoryList) {
    handleRoute<RequestDirectoryItemList, ResponseDirectoryItemList> { query ->
      service.list(this, query)
    }
  }
}

