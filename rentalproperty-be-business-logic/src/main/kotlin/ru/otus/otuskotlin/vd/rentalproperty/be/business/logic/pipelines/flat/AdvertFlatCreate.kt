package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.flat

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.helpers.validation
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.CompletePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.InitializePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.flat.AdvertFlatCreateStub
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorIntInRange
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorStringNonEmpty
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object AdvertFlatCreate : IOperation<BeContext> by pipeline({
  execute(InitializePipeline)

  execute(AdvertFlatCreateStub)

  validation {
    validate<Double?> {
      on { requestAdvertFlat.price }
      validator(
        ValidatorIntInRange(
          field = "price",
          min = 1.0
        )
      )
    }
    validate<String?> {
      on { requestAdvertFlat.name }
      validator(
        ValidatorStringNonEmpty(
          field = "name",
          message = "You must provide non-empty name for the advert flat"
        )
      )
    }
  }

  execute(CompletePipeline)
})