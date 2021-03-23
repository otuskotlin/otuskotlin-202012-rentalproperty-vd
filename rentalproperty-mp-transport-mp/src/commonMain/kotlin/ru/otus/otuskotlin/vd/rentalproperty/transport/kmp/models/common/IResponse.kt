package ru.otus.otuskotlin.marketplace.transport.kmp.models.common

import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ErrorDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.IDebug
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ResponseStatusDto

interface IResponse {
  val responseId: String?
  val onRequest: String?
  val endTime: String?
  val errors: List<ErrorDto>?
  val status: ResponseStatusDto?
  val debug: IDebug?
}

