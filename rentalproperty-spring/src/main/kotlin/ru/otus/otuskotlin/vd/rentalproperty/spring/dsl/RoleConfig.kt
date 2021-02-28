package ru.otus.otuskotlin.vd.rentalproperty.spring.dsl

import ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model.RoleDsl
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.PrivilegeEnum
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.RoleEnum

@UserDSL
class RoleConfig {
  private var name: String = RoleEnum.USER.name
  private var privileges: MutableSet<PrivilegeEnum> = mutableSetOf()
  private val _roleDsls: MutableSet<RoleDsl> = mutableSetOf()
  val roleDsls: Set<RoleDsl>
    get() = _roleDsls.toSet()

  fun add(roleDsl: RoleDsl) = _roleDsls.add(roleDsl)

  fun build() = RoleDsl(
    name = name,
    privileges = privileges.toMutableSet()
  )

  fun name(role: RoleEnum) {
    name = role.name
  }

  fun name(block: RoleNameConf.() -> Unit) {
    val roleNameConf = RoleNameConf().apply(block)
    name = roleNameConf.name
  }

  fun privileges(block: PrivilegeConf.() -> Unit) {
    val privilegeConf = PrivilegeConf().apply(block)
    privileges = privilegeConf.privileges.toMutableSet()
  }
}

fun role(block: RoleConfig.() -> Unit) = RoleConfig().apply(block).build()
