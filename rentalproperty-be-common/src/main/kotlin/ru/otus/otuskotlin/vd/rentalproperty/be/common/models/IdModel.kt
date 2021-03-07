package ru.otus.otuskotlin.vd.rentalproperty.be.common.models

inline class IdModel(
  override val id: String
) : IMpIdModel {
  companion object {
    val NONE = IdModel("")
  }
}
