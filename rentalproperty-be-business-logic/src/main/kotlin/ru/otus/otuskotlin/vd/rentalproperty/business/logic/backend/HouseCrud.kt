package ru.otus.otuskotlin.vd.rentalproperty.business.logic.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.business.logic.backend.pipelines.*

class HouseCrud {
  suspend fun filter(context: BeContext) {
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