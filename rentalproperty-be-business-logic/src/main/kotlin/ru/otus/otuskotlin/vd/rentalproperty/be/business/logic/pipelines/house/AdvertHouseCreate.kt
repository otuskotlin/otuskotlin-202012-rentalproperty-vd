package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.house

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.helpers.validation
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.CompletePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.InitializePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.house.AdvertHouseCreateStub
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorIntInRange
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorStringNonEmpty
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object AdvertHouseCreate : IOperation<BeContext> by pipeline({
  execute(InitializePipeline)

  execute(AdvertHouseCreateStub)

  validation {
    validate<Double?> {
      on { requestAdvertHouse.price }
      validator(
        ValidatorIntInRange(
          field = "price",
          min = 1.0
        )
      )
    }
    validate<String?> {
      on { requestAdvertHouse.name }
      validator(
        ValidatorStringNonEmpty(
          field = "name",
          message = "You must provide non-empty name for the advert house"
        )
      )
    }
  }

  execute(CompletePipeline)
})