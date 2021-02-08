package ru.otus.otuskotlin.vd.rentalproperty.ktor.model.person

import java.time.LocalDate

data class Profile(
  var fname: String = "",
  var mname: String = "",
  var lname: String = "",
  var dob: LocalDate = LocalDate.MIN,
  var phone: Phone = Phone.NONE,
)

