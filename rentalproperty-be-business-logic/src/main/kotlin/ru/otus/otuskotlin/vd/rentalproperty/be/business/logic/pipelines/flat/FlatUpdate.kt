package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.flat

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.CompletePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.InitializePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.flat.FlatUpdateStub
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorIntInRange
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorStringNonEmpty
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.validation.validation

object FlatUpdate : IOperation<BeContext> by pipeline({
  execute(InitializePipeline)

  execute(FlatUpdateStub)

  validation {
    validate<String?> {
      on { requestFlatId.id }
      validator(
        ValidatorStringNonEmpty(
          field = "flat-id",
          message = "Flat ID requested must not be empty"
        )
      )
    }
    validate<Double?> {
      on { requestFlat.area }
      validator(
        ValidatorIntInRange(
          field = "area",
          min = 1.0,
        )
      )
    }
  }

  execute(CompletePipeline)
})