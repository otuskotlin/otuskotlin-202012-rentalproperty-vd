package ru.otus.otuskotlin.vd.rentalproperty.dsl

import ru.otus.otuskotlin.vd.rentalproperty.model.person.Role
import ru.otus.otuskotlin.vd.rentalproperty.model.person.User

infix fun User.has(role: Role) {
  roles.add(role)
}
