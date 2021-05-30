package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

interface IGrantedAuthority {
  fun getAuthority(): String
}