package ru.otus.otuskotlin.vd.rentalproperty.model.person

inline class UserId(val id: String) {
  companion object {
    val NONE = UserId("")
  }
}
