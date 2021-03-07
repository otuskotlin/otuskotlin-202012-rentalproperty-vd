package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

import java.time.LocalDate

data class Profile(
  var fname: String = "",
  var mname: String = "",
  var lname: String = "",
  var dob: LocalDate = LocalDate.MIN,
  var phone: ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.Phone = ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.Phone.Companion.NONE,
)

