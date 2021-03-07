package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

inline class UserId(val id: String) {
  companion object {
    val NONE = ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserId("")
  }
}
