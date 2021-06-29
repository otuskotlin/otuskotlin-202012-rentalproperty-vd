package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.directory

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.helpers.validation
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.helpers.validationGrantedAuthority
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.AuthorizationPipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.CompletePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.InitializePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.QuerySetWorkMode
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.directory.DirectoryItemUpdateStub
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.Error
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.RolePrivileges
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorStringNonEmpty
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.operation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object DirectoryItemUpdate : IOperation<BeContext> by pipeline({
  execute(InitializePipeline)

  // Установка параметров контекста в зависимости от режима работы в запросе
  execute(QuerySetWorkMode)

  // Валидация учетных данных
  execute(AuthorizationPipeline)
  validationGrantedAuthority {
    setCheckValues(listOf(RolePrivileges.CONTENT_UPDATE.name))
  }

  // Обработка запроса стаба
  execute(DirectoryItemUpdateStub)

  // Валидация параметров запроса
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
    validate<String?> {
      on { requestDirectoryItem.name }
      validator(
        ValidatorStringNonEmpty(
          field = "name",
          message = "Directory item name requested must not be empty"
        )
      )
    }
  }

  // Добавление в репозитарий, ответ сохраняется в контексте
  operation {
    startIf { status == BeContextStatus.RUNNING }
    execute {
      try {
        directoryRepo.update(this)
        status = BeContextStatus.FINISHING
      } catch (t: Throwable) {
        status = BeContextStatus.FAILING
        errors.add(
          Error(
            code = "directory-repo-update-error",
            message = t.message ?: ""
          )
        )
      }
    }
  }

  // Подготовка ответа
  execute(CompletePipeline)
})