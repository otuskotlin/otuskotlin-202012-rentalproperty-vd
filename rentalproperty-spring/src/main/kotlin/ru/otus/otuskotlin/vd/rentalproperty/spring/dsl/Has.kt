package ru.otus.otuskotlin.vd.rentalproperty.spring.dsl

import ru.otus.otuskotlin.vd.rentalproperty.spring.model.person.Role
import ru.otus.otuskotlin.vd.rentalproperty.spring.model.person.User

infix fun User.has(role: Role) {
  roles.add(role)
}
