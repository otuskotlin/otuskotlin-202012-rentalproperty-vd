package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common

import kotlinx.serialization.Serializable

@Serializable
enum class ItemPermission {
  READ,
  UPDATE,
  DELETE,
}
