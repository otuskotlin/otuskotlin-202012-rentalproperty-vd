package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.house.*
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext

class AdvertHouseCrud {
  suspend fun list(context: BeContext) {
    AdvertHouseFilter.execute(context.apply(this::configureContext))
  }

  suspend fun create(context: BeContext) {
    AdvertHouseCreate.execute(context.apply(this::configureContext))
  }

  suspend fun read(context: BeContext) {
    AdvertHouseRead.execute(context.apply(this::configureContext))
  }

  suspend fun update(context: BeContext) {
    AdvertHouseUpdate.execute(context.apply(this::configureContext))
  }

  suspend fun delete(context: BeContext) {
    AdvertHouseDelete.execute(context.apply(this::configureContext))
  }

  private fun configureContext(context: BeContext) {

  }
}