package ru.otus.otuskotlin.vd.rentalproperty.spring

import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.person.Role
import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.person.User
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.RoleEnum
import ru.otus.otuskotlin.vd.rentalproperty.spring.util.RandomUtil

abstract class BaseTest {
  fun getTestUser(): User {
    return User(
      RandomUtil().getRandomStringAlpha(10),
      mutableSetOf(Role(RoleEnum.USER.name))
    )
  }
}