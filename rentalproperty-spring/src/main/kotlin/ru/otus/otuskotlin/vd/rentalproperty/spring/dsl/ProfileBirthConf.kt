package ru.otus.otuskotlin.vd.rentalproperty.spring.dsl

import java.time.LocalDate

@UserDSL
class ProfileBirthConf {
  var date: LocalDate = LocalDate.MIN
}
