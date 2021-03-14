package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media

inline class MediaFileIdModel(
  val id: String
) {
  companion object {
    val NONE = MediaFileIdModel("")
  }
}
