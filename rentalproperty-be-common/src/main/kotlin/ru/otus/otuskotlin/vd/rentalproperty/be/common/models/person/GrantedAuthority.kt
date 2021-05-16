package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

class GrantedAuthority(
  val value: String = ""
) : IGrantedAuthority {
  override fun getAuthority(): String = value
}
