package ru.otus.otuskotlin.vd.rentalproperty.ktor.models.person

inline class Email(val value: String) {
  companion object {
    val NONE = Email("")
  }
}
