package ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model

import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.PrivilegeEnum

data class RoleDsl(
  val name: String,
  var privileges: MutableSet<PrivilegeEnum> = mutableSetOf()
)
