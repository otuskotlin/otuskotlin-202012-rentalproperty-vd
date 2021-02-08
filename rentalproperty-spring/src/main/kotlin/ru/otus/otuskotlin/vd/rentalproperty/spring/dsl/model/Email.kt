package ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model

inline class Email(val value: String) {
  companion object {
    val NONE = Email("")
  }
}
