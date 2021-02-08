package ru.otus.otuskotlin.vd.rentalproperty.ktor.dsl

import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.RoleEnum

@UserDSL
class RoleNameConf {
  var name: String = RoleEnum.USER.name
}
