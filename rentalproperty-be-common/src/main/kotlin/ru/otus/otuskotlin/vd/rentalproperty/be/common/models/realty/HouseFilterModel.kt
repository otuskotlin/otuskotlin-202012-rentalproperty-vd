package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.SortModel

data class HouseFilterModel(
  val text: String = "",
  val includeDescription: Boolean = false,
  val sortBy: SortModel = SortModel.NONE,
  val offset: Int = Int.MIN_VALUE,
  val count: Int = Int.MIN_VALUE,
) {
  companion object {
    val NONE = HouseFilterModel()
  }
}
