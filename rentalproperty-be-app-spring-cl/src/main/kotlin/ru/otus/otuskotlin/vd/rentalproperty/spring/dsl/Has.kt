package ru.otus.otuskotlin.vd.rentalproperty.spring.dsl

import ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model.RoleDsl
import ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model.UserDsl

infix fun UserDsl.has(roleDsl: RoleDsl) {
  roleDsls.add(roleDsl)
}
