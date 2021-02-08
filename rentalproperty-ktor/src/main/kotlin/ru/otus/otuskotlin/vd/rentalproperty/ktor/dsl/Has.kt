package ru.otus.otuskotlin.vd.rentalproperty.ktor.dsl

import ru.otus.otuskotlin.vd.rentalproperty.ktor.model.person.Role
import ru.otus.otuskotlin.vd.rentalproperty.ktor.model.person.User

infix fun User.has(role: Role) {
  roles.add(role)
}
