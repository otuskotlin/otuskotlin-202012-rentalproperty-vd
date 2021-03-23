package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

data class Role(
  val name: String,
  var privileges: MutableSet<PrivilegeEnum> = mutableSetOf()
)
