package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.directory

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.CompletePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.InitializePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.directory.DirectoryItemReadStub
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorStringNonEmpty
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.validation.validation

object DirectoryItemRead : IOperation<BeContext> by pipeline({
  execute(InitializePipeline)

  execute(DirectoryItemReadStub)

  validation {
    validate<String?> {
      on { requestDirectoryItemId.id }
      validator(
        ValidatorStringNonEmpty(
          field = "dir-id",
          message = "Directory item ID requested must not be empty"
        )
      )
    }
  }

  execute(CompletePipeline)
})