package ru.otus.otuskotlin.vd.rentalproperty.spring.model.person

inline class Email(val value: String) {
  companion object {
    val NONE = Email("")
  }
}
