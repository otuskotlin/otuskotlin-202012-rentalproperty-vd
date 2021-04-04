package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.house

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.CompletePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.InitializePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.house.HouseUpdateStub
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorIntInRange
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorStringNonEmpty
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.validation.validation

object HouseUpdate : IOperation<BeContext> by pipeline({
  execute(InitializePipeline)

  execute(HouseUpdateStub)

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
    validate<Double?> {
      on { requestHouse.area }
      validator(
        ValidatorIntInRange(
          field = "area",
          min = 1.0,
        )
      )
    }
    validate<String?> {
      on { requestHouse.address }
      validator(
        ValidatorStringNonEmpty(
          field = "address",
          message = "You must provide non-empty address for the house"
        )
      )
    }
  }

  execute(CompletePipeline)
})