package ru.otus.otuskotlin.vd.rentalproperty.model

inline class UserId(val id: String) {
  companion object {
    val NONE = UserId("")
  }
}
