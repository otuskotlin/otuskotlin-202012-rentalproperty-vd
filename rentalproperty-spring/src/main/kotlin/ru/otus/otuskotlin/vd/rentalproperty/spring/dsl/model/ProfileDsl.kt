package ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model

import java.time.LocalDate

data class ProfileDsl(
  var fname: String = "",
  var mname: String = "",
  var lname: String = "",
  var dob: LocalDate = LocalDate.MIN,
  var phone: Phone = Phone.NONE,
)

