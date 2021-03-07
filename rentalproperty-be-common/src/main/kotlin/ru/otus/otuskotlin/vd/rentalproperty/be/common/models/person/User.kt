package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

data class User(
  var id: ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserId = ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserId.Companion.NONE,
  var email: ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.Email = ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.Email.Companion.NONE,
  var profile: ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.Profile,
  var roles: MutableSet<ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.Role> = mutableSetOf()
)