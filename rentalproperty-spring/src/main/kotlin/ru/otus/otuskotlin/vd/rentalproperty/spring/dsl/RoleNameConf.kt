package ru.otus.otuskotlin.vd.rentalproperty.spring.dsl

import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.RoleEnum

@UserDSL
class RoleNameConf {
  var name: String = RoleEnum.USER.name
}
