package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

inline class MpRoomIdModel(
  val id: String
) {
  companion object {
    val NONE = MpRoomIdModel("")
  }
}
