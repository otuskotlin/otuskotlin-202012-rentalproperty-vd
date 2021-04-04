package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

data class RoomFilterModel(
  val text: String = ""
) {
  companion object {
    val NONE = RoomFilterModel()
  }
}
