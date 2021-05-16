package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.house

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.helpers.validation
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.CompletePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.InitializePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.house.HouseDeleteStub
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorStringNonEmpty
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object HouseDelete : IOperation<BeContext> by pipeline({
  execute(InitializePipeline)

  execute(HouseDeleteStub)

  validation {
    validate<String?> {
      on { requestHouseId.id }
      validator(
        ValidatorStringNonEmpty(
          field = "house-id",
          message = "House ID requested must not be empty"
        )
      )
    }
  }

  execute(CompletePipeline)
})