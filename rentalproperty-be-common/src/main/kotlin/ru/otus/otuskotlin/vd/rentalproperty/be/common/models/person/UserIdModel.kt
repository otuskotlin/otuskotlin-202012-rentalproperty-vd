package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

inline class UserIdModel(val id: String) {
  companion object {
    val NONE = UserIdModel("")
  }
}
