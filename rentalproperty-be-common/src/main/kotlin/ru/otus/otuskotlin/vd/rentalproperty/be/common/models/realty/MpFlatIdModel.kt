package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

inline class MpFlatIdModel(
  override val id: String
) : ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IMpIdModel {
  companion object {
    val NONE = ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpFlatIdModel("")
  }
}
