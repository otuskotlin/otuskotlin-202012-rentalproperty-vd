package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.flat

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.helpers.validation
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.CompletePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.InitializePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.flat.AdvertFlatReadStub
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorStringNonEmpty
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object AdvertFlatRead : IOperation<BeContext> by pipeline({
  execute(InitializePipeline)

  execute(AdvertFlatReadStub)

  validation {
    validate<String?> {
      on { requestAdvertFlatId.id }
      validator(
        ValidatorStringNonEmpty(
          field = "advertFlat-id",
          message = "Advert flat ID requested must not be empty"
        )
      )
    }
  }

  execute(CompletePipeline)
})