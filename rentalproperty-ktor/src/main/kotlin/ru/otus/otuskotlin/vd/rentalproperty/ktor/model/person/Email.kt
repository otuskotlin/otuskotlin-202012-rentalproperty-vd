package ru.otus.otuskotlin.vd.rentalproperty.ktor.model.person

inline class Email(val value: String) {
  companion object {
    val NONE = Email("")
  }
}
