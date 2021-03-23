package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert

inline class AdvertIdModel(val id: String) {
  companion object {
    val NONE = AdvertIdModel("")
  }
}
