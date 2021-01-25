package ru.otus.otuskotlin.vd.rentalproperty.dsl

import ru.otus.otuskotlin.vd.rentalproperty.enums.RoleEnum

@UserDSL
class RoleNameConf {
  var name: String = RoleEnum.USER.name
}
