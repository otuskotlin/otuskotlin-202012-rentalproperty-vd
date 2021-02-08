package ru.otus.otuskotlin.vd.rentalproperty.ktor.model

inline class Id(val id: String) {
  companion object {
    val NONE = Id("")
  }
}
