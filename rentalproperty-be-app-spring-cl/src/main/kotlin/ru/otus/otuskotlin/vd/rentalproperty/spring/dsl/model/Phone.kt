package ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model

inline class Phone(val phone: String) {
  companion object {
    val NONE = Phone("")
  }
}
