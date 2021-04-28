package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.flat

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.CompletePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.InitializePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.QuerySetWorkMode
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.flat.FlatUpdateStub
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.Error
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorStringNonEmpty
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.operation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.validation.validation

object FlatUpdate : IOperation<BeContext> by pipeline({
  execute(InitializePipeline)

  // Установка параметров контекста в зависимости от режима работы в запросе
  execute(QuerySetWorkMode)

  // Обработка запроса стаба
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
//    validate<Double?> {
//      on { requestFlat.area }
//      validator(
//        ValidatorIntInRange(
//          field = "area",
//          min = 1.0,
//        )
//      )
//    }
  }

  // Обновление данных в репозитарии, ответ сохраняется в контексте
  operation {
    startIf { status == BeContextStatus.RUNNING }
    execute {
      try {
        flatRepo.update(this)
        status = BeContextStatus.FINISHING
      } catch (t: Throwable) {
        status = BeContextStatus.FAILING
        errors.add(
          Error(
            code = "flat-repo-update-error",
            message = t.message ?: ""
          )
        )
      }
    }
  }

  // Подготовка ответа
  execute(CompletePipeline)
})