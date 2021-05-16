package ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IError
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.WorkMode
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.exceptions.WrongBeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ErrorDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ResponseStatusDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.WorkModeDto

internal fun WorkModeDto?.toModel() = when (this) {
  WorkModeDto.PROD -> WorkMode.PROD
  WorkModeDto.TEST -> WorkMode.TEST
  else -> WorkMode.DEFAULT
}

internal fun WorkMode.toTransport() = when (this) {
  WorkMode.PROD -> WorkModeDto.PROD
  WorkMode.TEST -> WorkModeDto.TEST
}

fun <T : IRequest> BeContext.setQuery(query: T, block: BeContext.() -> Unit) = apply {
  onRequest = query.requestId ?: ""
  block()
}

fun IError.toTransport() = ErrorDto(
  message = message
)

fun BeContextStatus.toTransport(): ResponseStatusDto = when (this) {
    BeContextStatus.NONE -> throw WrongBeContextStatus(this)
    BeContextStatus.RUNNING -> throw WrongBeContextStatus(this)
    BeContextStatus.FINISHING -> ResponseStatusDto.SUCCESS
    BeContextStatus.FAILING -> throw WrongBeContextStatus(this)
    BeContextStatus.SUCCESS -> ResponseStatusDto.SUCCESS
    BeContextStatus.ERROR -> ResponseStatusDto.BAD_REQUEST
}
