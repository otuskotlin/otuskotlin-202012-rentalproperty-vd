package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IMpIdModel

inline class MpFlatIdModel(
  override val id: String
) : IMpIdModel {
  companion object {
    val NONE = MpFlatIdModel("")
  }
}
