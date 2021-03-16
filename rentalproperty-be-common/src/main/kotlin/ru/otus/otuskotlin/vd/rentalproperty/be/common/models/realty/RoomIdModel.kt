package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

inline class RoomIdModel(
  val id: String
) {
  companion object {
    val NONE = RoomIdModel("")
  }
}
