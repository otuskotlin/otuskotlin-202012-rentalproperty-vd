package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

inline class DirectoryItemIdModel(val id: String) {
  companion object {
    val NONE = DirectoryItemIdModel("")
  }
}
