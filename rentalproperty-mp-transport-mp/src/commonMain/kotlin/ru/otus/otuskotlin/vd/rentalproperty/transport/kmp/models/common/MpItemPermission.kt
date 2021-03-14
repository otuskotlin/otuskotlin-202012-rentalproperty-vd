package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common

import kotlinx.serialization.Serializable

@Serializable
enum class MpItemPermission {
  READ,
  UPDATE,
  DELETE,
}
