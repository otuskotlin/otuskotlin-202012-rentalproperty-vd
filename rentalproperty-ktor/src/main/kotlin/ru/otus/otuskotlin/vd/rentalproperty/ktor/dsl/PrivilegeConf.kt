package ru.otus.otuskotlin.vd.rentalproperty.ktor.dsl

import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.PrivilegeEnum

@UserDSL
class PrivilegeConf {
  private val _privileges: MutableSet<PrivilegeEnum> = mutableSetOf()
  val privileges: Set<PrivilegeEnum>
    get() = _privileges.toSet()

  fun add(privileges: PrivilegeEnum) = _privileges.add(privileges)
  fun add(privilegesStr: String) = add(PrivilegeEnum.valueOf(privilegesStr))

  operator fun String.unaryPlus() = add(this)
  operator fun PrivilegeEnum.unaryPlus() = add(this)
}
