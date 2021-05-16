package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.flat

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.helpers.validation
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.CompletePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.InitializePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.QuerySetWorkMode
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.flat.FlatCreateStub
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.Error
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IError
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.PrincipalModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorIntInRange
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.operation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object FlatCreate : IOperation<BeContext> by pipeline({
  execute(InitializePipeline)

  // Установка параметров контекста в зависимости от режима работы в запросе
  execute(QuerySetWorkMode)

  // Обработка запроса стаба
  execute(FlatCreateStub)

  // Валидация учетных данных
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
      } else {
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

  // Валидация параметров запроса
  validation {
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

  // Добавление в репозитарий, ответ сохраняется в контексте
  operation {
    startIf { status == BeContextStatus.RUNNING }
    execute {
      try {
        flatRepo.create(this)
        status = BeContextStatus.FINISHING
      } catch (t: Throwable) {
        status = BeContextStatus.FAILING
        errors.add(
          Error(
            code = "flat-repo-create-error",
            message = t.message ?: ""
          )
        )
      }
    }
  }

  // Подготовка ответа
  execute(CompletePipeline)
})