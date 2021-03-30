package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.CompletePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.InitializePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.AdvertHouseDeleteStub
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorStringNonEmpty
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.validation.validation

object AdvertHouseDelete : IOperation<BeContext> by pipeline({
  execute(InitializePipeline)

  execute(AdvertHouseDeleteStub)

  validation {
    validate<String?> {
      on { requestAdvertHouseId.id }
      validator(
        ValidatorStringNonEmpty(
          field = "advertHouse-id",
          message = "Advert house ID requested must not be empty"
        )
      )
    }
  }

  execute(CompletePipeline)
})