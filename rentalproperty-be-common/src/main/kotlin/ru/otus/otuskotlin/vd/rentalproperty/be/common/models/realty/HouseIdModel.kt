package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty


inline class HouseIdModel(
  val id: String
) {
  companion object {
    val NONE = HouseIdModel("")
  }
}
