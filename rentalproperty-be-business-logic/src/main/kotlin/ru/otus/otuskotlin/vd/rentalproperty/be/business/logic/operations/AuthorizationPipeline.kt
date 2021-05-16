package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.Error
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IError
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.PrincipalModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.operation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object AuthorizationPipeline : IOperation<BeContext> by pipeline({
  operation {
    startIf { status == BeContextStatus.RUNNING }
    execute {
      if (principal == PrincipalModel.NONE) {
        errors.add(
          Error(
            code = "unauthorized",
            group = IError.Group.AUTH,
            level = IError.Level.ERROR,
            message = "User is unauthorized"
          )
        )
        status = BeContextStatus.ERROR
      }
    }
  }
  operation {
    startIf { status == BeContextStatus.RUNNING }
    execute {
      if (!principal.enabled) {
        errors.add(
          Error(
            code = "forbidden",
            group = IError.Group.AUTH,
            level = IError.Level.ERROR,
            message = "User is disable"
          )
        )
        status = BeContextStatus.ERROR
      }
    }
  }
})