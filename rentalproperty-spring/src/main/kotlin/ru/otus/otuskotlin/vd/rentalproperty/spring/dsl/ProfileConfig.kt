package ru.otus.otuskotlin.vd.rentalproperty.spring.dsl

import ru.otus.otuskotlin.vd.rentalproperty.spring.model.person.Phone
import ru.otus.otuskotlin.vd.rentalproperty.spring.model.person.Profile
import java.time.LocalDate

@UserDSL
class ProfileConfig {
  private var fname: String = ""
  private var mname: String = ""
  private var lname: String = ""
  private var dob: LocalDate = LocalDate.MIN
  private var phone: Phone = Phone.NONE

  fun build() = Profile(
    fname = fname,
    mname = mname,
    lname = lname,
    dob = dob,
    phone = phone,
  )

  fun name(block: ProfileNameConf.() -> Unit) {
    val nameConf = ProfileNameConf().apply(block)
    fname = nameConf.first
    mname = nameConf.midle
    lname = nameConf.last
  }

  fun birth(block: ProfileBirthConf.() -> Unit) {
    val birthConf = ProfileBirthConf().apply(block)
    dob = birthConf.date
  }

  fun contacts(block: ProfileContactsConf.() -> Unit) {
    val contactsConf = ProfileContactsConf().apply(block)
    phone = Phone(contactsConf.phone)
  }
}

fun profile(block: ProfileConfig.() -> Unit) = ProfileConfig().apply(block).build()
