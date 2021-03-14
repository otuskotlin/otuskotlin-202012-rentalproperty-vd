package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty


inline class MpFlatIdModel(
  val id: String
) {
  companion object {
    val NONE = MpFlatIdModel("")
  }
}
