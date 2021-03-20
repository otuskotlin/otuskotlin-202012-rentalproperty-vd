package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common

import kotlinx.serialization.Serializable

@Serializable
enum class ItemPermission {
  READ,
  UPDATE,
  DELETE,
}
