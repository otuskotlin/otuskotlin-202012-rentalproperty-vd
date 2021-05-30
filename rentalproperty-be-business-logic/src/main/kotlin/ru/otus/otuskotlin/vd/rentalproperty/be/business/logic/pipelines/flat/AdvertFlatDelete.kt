package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.flat

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.helpers.validation
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.helpers.validationGrantedAuthority
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.AuthorizationPipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.CompletePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.InitializePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.QuerySetWorkMode
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.flat.AdvertFlatDeleteStub
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.RolePrivileges
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorStringNonEmpty
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object AdvertFlatDelete : IOperation<BeContext> by pipeline({
  execute(InitializePipeline)

  // Установка параметров контекста в зависимости от режима работы в запросе
  execute(QuerySetWorkMode)

  // Валидация учетных данных
  execute(AuthorizationPipeline)
  validationGrantedAuthority {
    setCheckValues(listOf(RolePrivileges.CONTENT_DELETE.name))
  }

  // Обработка запроса стаба
  execute(AdvertFlatDeleteStub)

  // Валидация параметров запроса
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