package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
class User(
  var email: String,
  @OneToOne var profile: Profile,
  @ManyToMany var roles: MutableSet<Role> = mutableSetOf(),
  @Id @GeneratedValue var id: Long? = null
)

@Entity
class Profile(
  var fname: String,
  var mname: String,
  var lname: String,
  var dob: LocalDate,
  var phone: String,
  @Id @GeneratedValue var id: Long? = null
)
