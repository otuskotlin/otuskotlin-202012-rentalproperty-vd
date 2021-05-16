package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

import java.time.LocalDate

data class ProfileModel(
  var fname: String = "",
  var mname: String = "",
  var lname: String = "",
  var dob: LocalDate = LocalDate.MIN,
  var phone: Phone = Phone.NONE,
) {
  companion object {
    val NONE = ProfileModel()
  }
}


