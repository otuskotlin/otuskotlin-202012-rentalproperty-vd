package ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums

import kotlinx.serialization.Serializable

@Serializable
enum class RoleEnum {
  ADMIN,
  MANAGER,
  USER
}
