package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty


inline class MpHouseIdModel(
  val id: String
) {
  companion object {
    val NONE = MpHouseIdModel("")
  }
}
