package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

data class MpHouseFilterModel(
  val text: String = ""
) {
  companion object {
    val NONE = MpHouseFilterModel()
  }
}
