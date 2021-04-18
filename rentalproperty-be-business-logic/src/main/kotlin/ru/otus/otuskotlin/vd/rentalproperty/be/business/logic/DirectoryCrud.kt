package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.directory.*
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext

class DirectoryCrud {
  suspend fun list(context: BeContext) {
    DirectoryItemFilter.execute(context.apply(this::configureContext))
  }

  suspend fun create(context: BeContext) {
    DirectoryItemCreate.execute(context.apply(this::configureContext))
  }

  suspend fun read(context: BeContext) {
    DirectoryItemRead.execute(context.apply(this::configureContext))
  }

  suspend fun update(context: BeContext) {
    DirectoryItemUpdate.execute(context.apply(this::configureContext))
  }

  suspend fun delete(context: BeContext) {
    DirectoryItemDelete.execute(context.apply(this::configureContext))
  }

  private fun configureContext(context: BeContext) {

  }
}