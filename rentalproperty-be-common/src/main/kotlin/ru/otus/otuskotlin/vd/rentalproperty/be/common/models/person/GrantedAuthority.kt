package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

class GrantedAuthority(
  private var value: String = ""
) : IGrantedAuthority {
  override fun getAuthority(): String = value
}
