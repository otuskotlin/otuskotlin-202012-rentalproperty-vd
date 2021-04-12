package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.flat.*
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext

class FlatCrud {
  suspend fun list(context: BeContext) {
    FlatFilter.execute(context.apply(this::configureContext))
  }

  suspend fun create(context: BeContext) {
    FlatCreate.execute(context.apply(this::configureContext))
  }

  suspend fun read(context: BeContext) {
    FlatRead.execute(context.apply(this::configureContext))
  }

  suspend fun update(context: BeContext) {
    FlatUpdate.execute(context.apply(this::configureContext))
  }

  suspend fun delete(context: BeContext) {
    FlatDelete.execute(context.apply(this::configureContext))
  }

  private fun configureContext(context: BeContext) {

  }
}