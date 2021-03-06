package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.flat

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.helpers.validation
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.helpers.validationGrantedAuthority
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.AuthorizationPipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.CompletePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.InitializePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.QuerySetWorkMode
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.flat.FlatReadStub
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.Error
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.RolePrivileges
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorStringNonEmpty
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.operation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object FlatRead : IOperation<BeContext> by pipeline({
  execute(InitializePipeline)

  // Установка параметров контекста в зависимости от режима работы в запросе
  execute(QuerySetWorkMode)

  // Валидация учетных данных
  execute(AuthorizationPipeline)
  validationGrantedAuthority {
    setCheckValues(listOf(RolePrivileges.CONTENT_READ.name))
  }
//  validationAuthorization {
//    validate<List<String>> {
//      on { principal.authorities.map { a -> a.value } }
//      validator(
//        ValidatorHasInList(
//          values = listOf(RolePrivileges.CONTENT_READ.name),
//          field = "authorities",
//          message = "You don't have sufficient authority",
//        )
//      )
//    }
//  }

  // Обработка запроса стаба
  execute(FlatReadStub)

  // Валидация параметров запроса
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
  }

  // Чтение данных из репозитария, ответ сохраняется в контексте
  operation {
    startIf { status == BeContextStatus.RUNNING }
    execute {
      try {
        flatRepo.read(this)
        status = BeContextStatus.FINISHING
      } catch (t: Throwable) {
        status = BeContextStatus.FAILING
        errors.add(
          Error(
            code = "flat-repo-read-error",
            message = t.message ?: ""
          )
        )
      }
    }
  }

  // Подготовка ответа
  execute(CompletePipeline)
})