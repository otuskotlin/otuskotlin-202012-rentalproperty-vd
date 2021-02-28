package ru.otus.otuskotlin.vd.rentalproperty.ktor.models.person

import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.PrivilegeEnum

data class Role(
  val name: String,
  var privileges: MutableSet<PrivilegeEnum> = mutableSetOf()
)
