package ru.otus.otuskotlin.vd.rentalproperty.mappers.backend

import ru.otus.otuskotlin.marketplace.backend.mappers.kmp.exceptions.WrongBeContextStatus
import ru.otus.otuskotlin.marketplace.transport.kmp.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IError
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ErrorDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ResponseStatusDto

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
