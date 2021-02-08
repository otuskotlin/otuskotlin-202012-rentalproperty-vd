package ru.otus.otuskotlin.vd.rentalproperty.spring.model.person

inline class UserId(val id: String) {
  companion object {
    val NONE = UserId("")
  }
}
