package ru.otus.otuskotlin.marketplace.transport.kmp.models.common

import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.IMpDebug

interface IMpRequest {
  val requestId: String?
  val onResponse: String?
  val startTime: String?
  val debug: IMpDebug?
}
