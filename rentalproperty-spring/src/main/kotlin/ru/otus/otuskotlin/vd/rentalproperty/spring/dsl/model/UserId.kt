package ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model

inline class UserId(val id: String) {
  companion object {
    val NONE = UserId("")
  }
}
