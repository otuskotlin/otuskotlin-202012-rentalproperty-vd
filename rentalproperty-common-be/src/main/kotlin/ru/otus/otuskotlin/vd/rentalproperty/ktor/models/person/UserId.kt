package ru.otus.otuskotlin.vd.rentalproperty.ktor.models.person

inline class UserId(val id: String) {
  companion object {
    val NONE = UserId("")
  }
}
