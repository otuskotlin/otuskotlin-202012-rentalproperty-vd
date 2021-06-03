package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import java.util.*

inline class HouseIdModel(
  val id: String
) {
  companion object {
    val NONE = HouseIdModel("")
  }

  fun asString() = id
  fun asUUID(): UUID = UUID.fromString(id)
}
