package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

import ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.PrivilegeEnum

data class Role(
  val name: String,
  var privileges: MutableSet<PrivilegeEnum> = mutableSetOf()
)
