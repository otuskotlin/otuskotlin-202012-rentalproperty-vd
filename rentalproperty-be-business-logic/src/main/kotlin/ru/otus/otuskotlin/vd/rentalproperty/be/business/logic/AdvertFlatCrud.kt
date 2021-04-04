package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.flat.*
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext

class AdvertFlatCrud {
  suspend fun list(context: BeContext) {
    AdvertFlatFilter.execute(context.apply(this::configureContext))
  }

  suspend fun create(context: BeContext) {
    AdvertFlatCreate.execute(context.apply(this::configureContext))
  }

  suspend fun read(context: BeContext) {
    AdvertFlatRead.execute(context.apply(this::configureContext))
  }

  suspend fun update(context: BeContext) {
    AdvertFlatUpdate.execute(context.apply(this::configureContext))
  }

  suspend fun delete(context: BeContext) {
    AdvertFlatDelete.execute(context.apply(this::configureContext))
  }

  private fun configureContext(context: BeContext) {

  }
}