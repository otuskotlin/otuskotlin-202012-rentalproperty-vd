package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common

interface IRequest {
  val requestId: String?
  val onResponse: String?
  val startTime: String?
  val debug: IDebug?
}
