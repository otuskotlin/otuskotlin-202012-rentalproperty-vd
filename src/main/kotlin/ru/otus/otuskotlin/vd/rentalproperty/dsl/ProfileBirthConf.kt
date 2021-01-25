package ru.otus.otuskotlin.vd.rentalproperty.dsl

import java.time.LocalDate

@UserDSL
class ProfileBirthConf {
  var date: LocalDate = LocalDate.MIN
}
