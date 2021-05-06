package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

import java.util.*

inline class DirectoryItemIdModel(
  val id: String
) {
  companion object {
    val NONE = DirectoryItemIdModel("")
  }

  fun asString() = id
  fun asUUID(): UUID = UUID.fromString(id)
}
