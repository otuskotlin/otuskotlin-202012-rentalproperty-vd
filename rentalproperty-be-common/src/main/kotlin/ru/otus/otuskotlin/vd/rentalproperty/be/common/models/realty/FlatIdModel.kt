package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty


inline class FlatIdModel(
  val id: String
) {
  companion object {
    val NONE = FlatIdModel("")
  }
}
