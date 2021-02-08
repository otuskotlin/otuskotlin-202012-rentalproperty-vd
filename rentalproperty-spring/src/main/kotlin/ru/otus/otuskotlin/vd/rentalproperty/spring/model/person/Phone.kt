package ru.otus.otuskotlin.vd.rentalproperty.spring.model.person

inline class Phone(val phone: String) {
  companion object {
    val NONE = Phone("")
  }
}
