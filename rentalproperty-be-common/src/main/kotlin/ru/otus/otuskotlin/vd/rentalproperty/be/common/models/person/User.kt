package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

data class User(
  var id: UserId = UserId.Companion.NONE,
  var email: Email = Email.Companion.NONE,
  var profile: Profile,
  var roles: MutableSet<Role> = mutableSetOf()
)