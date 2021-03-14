package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

data class HouseFilterModel(
  val text: String = ""
) {
  companion object {
    val NONE = HouseFilterModel()
  }
}
