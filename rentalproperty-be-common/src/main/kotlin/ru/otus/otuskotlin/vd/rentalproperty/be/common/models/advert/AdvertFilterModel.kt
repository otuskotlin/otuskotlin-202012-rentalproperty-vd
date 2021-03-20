package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert

data class AdvertFilterModel(
  val text: String = ""
) {
  companion object {
    val NONE = AdvertFilterModel()
  }
}
