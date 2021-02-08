package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class Role(
  val name: String,
  @ManyToMany var privileges: MutableSet<Privilege> = mutableSetOf(),
  @Id @GeneratedValue var id: Long? = null
)

@Entity
class Privilege(
  val name: String,
  @Id @GeneratedValue var id: Long? = null
)