package ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model

data class UserDsl(
  var id: UserId = UserId.NONE,
  var email: Email = Email.NONE,
  var profileDsl: ProfileDsl,
  var roleDsls: MutableSet<RoleDsl> = mutableSetOf()
)