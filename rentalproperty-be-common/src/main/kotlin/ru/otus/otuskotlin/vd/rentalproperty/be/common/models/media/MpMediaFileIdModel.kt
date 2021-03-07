package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IMpIdModel

inline class MpMediaFileIdModel(
  override val id: String
) : IMpIdModel {
  companion object {
    val NONE = MpMediaFileIdModel("")
  }
}
