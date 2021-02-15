package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.person

import javax.persistence.*

@Entity
class Role(
  @Column(nullable = false, unique = true)
  val name: String,
  @ManyToMany var privileges: MutableSet<Privilege> = mutableSetOf(),
  @Id @GeneratedValue var id: Long? = null
)

@Entity
class Privilege(
  @Column(nullable = false, unique = true)
  val name: String,
  @Id @GeneratedValue var id: Long? = null
)