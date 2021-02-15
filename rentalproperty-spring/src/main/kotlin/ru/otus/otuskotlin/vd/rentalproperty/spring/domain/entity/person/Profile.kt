package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.person

import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.BaseAuditEntity
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
class Profile(
  @Column(nullable = false, length = 50)
  @Size(min = 1, max = 50)
  @Pattern(regexp = "^\\w+-?\\w*$")
  var fname: String,

  @Column(length = 50)
  @Size(min = 1, max = 50)
  @Pattern(regexp = "^\\w+-?\\w*$")
  var mname: String? = null,

  @Column(length = 50)
  @Size(min = 1, max = 50)
  @Pattern(regexp = "^\\w+-?\\w*$")
  var lname: String? = null,

  var dob: LocalDate? = null,

  @Column(length = 15)
  @Size(min = 9, max = 15)
  @Pattern(regexp = "^([1-9]\\d{8,14})$")
  var phone: String? = null
) : BaseAuditEntity<Long>()
