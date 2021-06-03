package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import java.util.*

inline class FlatIdModel(
  val id: String
) {
  companion object {
    val NONE = FlatIdModel("")
  }

  fun asString() = id
  fun asUUID(): UUID = UUID.fromString(id)
}
