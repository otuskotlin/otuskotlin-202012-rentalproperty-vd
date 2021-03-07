package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person

inline class Phone(val phone: String) {
  companion object {
    val NONE = Phone("")
  }
}
