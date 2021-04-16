package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.routing.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helpers.handleRoute
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.DirectoryService
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.DirectoryRestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.*

fun Routing.directoryRouting(service: DirectoryService) {
  post(DirectoryRestEndpoints.directoryCreate) {
    handleRoute<RequestDirectoryItemCreate, ResponseDirectoryItemCreate> { query ->
      service.create(this, query)
    }
  }
  post(DirectoryRestEndpoints.directoryRead) {
    handleRoute<RequestDirectoryItemRead, ResponseDirectoryItemRead> { query ->
      service.read(this, query)
    }
  }
  post(DirectoryRestEndpoints.directoryUpdate) {
    handleRoute<RequestDirectoryItemUpdate, ResponseDirectoryItemUpdate> { query ->
      service.update(this, query)
    }
  }
  post(DirectoryRestEndpoints.directoryDelete) {
    handleRoute<RequestDirectoryItemDelete, ResponseDirectoryItemDelete> { query ->
      service.delete(this, query)
    }
  }
  post(DirectoryRestEndpoints.directoryList) {
    handleRoute<RequestDirectoryItemList, ResponseDirectoryItemList> { query ->
      service.list(this, query)
    }
  }
  post(DirectoryRestEndpoints.directoryAppliances) {
    handleRoute<RequestDirectoryItemList, ResponseDirectoryItemList> { query ->
      service.appliances(this, query)
    }
  }
}

