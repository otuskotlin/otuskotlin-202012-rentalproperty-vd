package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

data class FlatFilterModel(
  val text: String = ""
) {
  companion object {
    val NONE = FlatFilterModel()
  }
}
