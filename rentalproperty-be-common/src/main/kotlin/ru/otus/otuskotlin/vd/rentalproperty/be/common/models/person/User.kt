package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

data class User(
  var id: UserIdModel = UserIdModel.NONE,
  var email: Email = Email.NONE,
  var profile: Profile,
  var roles: MutableSet<Role> = mutableSetOf()
)