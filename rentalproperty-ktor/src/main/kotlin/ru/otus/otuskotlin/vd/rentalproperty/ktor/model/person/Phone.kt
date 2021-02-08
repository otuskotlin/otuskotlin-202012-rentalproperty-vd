package ru.otus.otuskotlin.vd.rentalproperty.ktor.model.person

inline class Phone(val phone: String) {
  companion object {
    val NONE = Phone("")
  }
}
