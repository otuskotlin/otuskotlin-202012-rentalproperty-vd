package ru.otus.otuskotlin.vd.rentalproperty.model

import ru.otus.otuskotlin.vd.rentalproperty.enums.PrivilegeEnum

data class Role(
  val name: String,
  var privileges: MutableSet<PrivilegeEnum> = mutableSetOf()
)
