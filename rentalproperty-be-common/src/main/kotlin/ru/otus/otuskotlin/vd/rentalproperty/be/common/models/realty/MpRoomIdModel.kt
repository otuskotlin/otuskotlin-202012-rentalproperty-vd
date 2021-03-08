package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IMpIdModel

inline class MpRoomIdModel(
  override val id: String
) : IMpIdModel {
  companion object {
    val NONE = MpRoomIdModel("")
  }
}
