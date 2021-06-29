package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.helpers

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.Error
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IError
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.ValidationResult
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.Operation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.Pipeline
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.validation.ValidationBuilder

fun Pipeline.Builder<BeContext>.validation(block: ValidationBuilder<BeContext>.() -> Unit) {
  execute(ValidationBuilder<BeContext>()
    .apply {
      startIf { status == BeContextStatus.RUNNING }
      errorHandler { vr: ValidationResult ->
        if (vr.isSuccess) return@errorHandler
        val errs = vr.errors.map { Error(message = it.message) }
        errors.addAll(errs)
        status = BeContextStatus.FAILING
      }
    }
    .apply(block)
    .build())
}

fun Pipeline.Builder<BeContext>.validationAuthorization(block: ValidationBuilder<BeContext>.() -> Unit) {
  execute(ValidationBuilder<BeContext>()
    .apply {
      startIf { status == BeContextStatus.RUNNING }
      errorHandler { vr: ValidationResult ->
        if (vr.isSuccess) return@errorHandler
        val errs = vr.errors.map {
          Error(
            code = "forbidden",
            group = IError.Group.AUTH,
            level = IError.Level.ERROR,
            message = it.message
          )
        }
        errors.addAll(errs)
        status = BeContextStatus.FAILING
      }
    }
    .apply(block)
    .build())
}

fun Pipeline.Builder<BeContext>.validationGrantedAuthority(block: Operation.Builder<BeContext>.() -> Unit) {
  execute(Operation.Builder<BeContext>()
    .apply(block)
    .apply {
      startIf { status == BeContextStatus.RUNNING }
      execute {
        if (!principal.authorities.map { a -> a.value }.containsAll(getCheckValues())) {
          errors.add(
            Error(
              code = "forbidden",
              group = IError.Group.AUTH,
              level = IError.Level.ERROR,
              message = "You don't have sufficient authority",
            )
          )
          status = BeContextStatus.ERROR
        }
      }
    }
    .build())
}