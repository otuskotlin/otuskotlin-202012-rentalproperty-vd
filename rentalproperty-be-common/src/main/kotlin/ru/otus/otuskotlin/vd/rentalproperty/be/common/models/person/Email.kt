package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

inline class Email(val value: String) {
  companion object {
    val NONE = Email("")
  }
}
