package ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model

inline class Id(val id: String) {
  companion object {
    val NONE = Id("")
  }
}
