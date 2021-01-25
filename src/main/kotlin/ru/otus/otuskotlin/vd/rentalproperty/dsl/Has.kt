package ru.otus.otuskotlin.vd.rentalproperty.dsl

import ru.otus.otuskotlin.vd.rentalproperty.model.Role
import ru.otus.otuskotlin.vd.rentalproperty.model.User

infix fun User.has(role: Role) {
  roles.add(role)
}
