package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media

import java.util.*

inline class MediaFileIdModel(
  val id: String
) {
  companion object {
    val NONE = MediaFileIdModel("")
  }

  fun asString() = id
  fun asUUID(): UUID = UUID.fromString(id)
}
