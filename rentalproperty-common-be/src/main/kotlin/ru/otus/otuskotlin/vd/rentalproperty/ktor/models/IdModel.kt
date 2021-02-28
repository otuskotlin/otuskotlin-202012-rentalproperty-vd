package ru.otus.otuskotlin.vd.rentalproperty.ktor.models

inline class IdModel(
  override val id: String
) : IMpIdModel {
  companion object {
    val NONE = IdModel("")
  }
}
