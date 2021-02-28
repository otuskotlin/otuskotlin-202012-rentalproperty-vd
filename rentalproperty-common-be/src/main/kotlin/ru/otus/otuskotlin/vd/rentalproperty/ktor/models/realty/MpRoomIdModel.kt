package ru.otus.otuskotlin.vd.rentalproperty.ktor.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.ktor.models.IMpIdModel

inline class MpRoomIdModel(
  override val id: String
) : IMpIdModel {
  companion object {
    val NONE = MpRoomIdModel("")
  }
}
