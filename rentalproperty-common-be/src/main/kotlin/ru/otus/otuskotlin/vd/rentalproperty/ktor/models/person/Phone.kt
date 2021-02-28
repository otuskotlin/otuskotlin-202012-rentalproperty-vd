package ru.otus.otuskotlin.vd.rentalproperty.ktor.models.person

inline class Phone(val phone: String) {
  companion object {
    val NONE = Phone("")
  }
}
