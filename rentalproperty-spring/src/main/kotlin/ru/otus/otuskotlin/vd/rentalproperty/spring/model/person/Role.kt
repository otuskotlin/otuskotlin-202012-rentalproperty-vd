package ru.otus.otuskotlin.vd.rentalproperty.spring.model.person

import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.PrivilegeEnum

data class Role(
  val name: String,
  var privileges: MutableSet<PrivilegeEnum> = mutableSetOf()
)
