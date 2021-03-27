package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common

interface IResponse {
  val responseId: String?
  val onRequest: String?
  val endTime: String?
  val errors: List<ErrorDto>?
  val status: ResponseStatusDto?
  val debug: IDebug?
}

