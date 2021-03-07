package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.person

import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.BaseAuditEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.OneToOne
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

@Entity
class User(
  @Email
  @Column(nullable = false, unique = true)
  var email: String,

  @NotEmpty
  @ManyToMany var roles: MutableSet<Role>,

  @OneToOne var profile: Profile? = null,

  @Column(nullable = false, columnDefinition = "boolean default true")
  var enabled: Boolean = true,
) : BaseAuditEntity<Long>()

