package ru.otus.otuskotlin.vd.rentalproperty.model

data class User(
  var id: UserId = UserId.NONE,
  var email: Email = Email.NONE,
  var profile: Profile,
  var roles: MutableSet<Role> = mutableSetOf()
)