package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media

inline class MpMediaFileIdModel(
  val id: String
) {
  companion object {
    val NONE = MpMediaFileIdModel("")
  }
}
