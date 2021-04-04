package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.house.*
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext

class HouseCrud {
  suspend fun list(context: BeContext) {
    HouseFilter.execute(context.apply(this::configureContext))
  }

  suspend fun create(context: BeContext) {
    HouseCreate.execute(context.apply(this::configureContext))
  }

  suspend fun read(context: BeContext) {
    HouseRead.execute(context.apply(this::configureContext))
  }

  suspend fun update(context: BeContext) {
    HouseUpdate.execute(context.apply(this::configureContext))
  }

  suspend fun delete(context: BeContext) {
    HouseDelete.execute(context.apply(this::configureContext))
  }

  private fun configureContext(context: BeContext) {

  }
}