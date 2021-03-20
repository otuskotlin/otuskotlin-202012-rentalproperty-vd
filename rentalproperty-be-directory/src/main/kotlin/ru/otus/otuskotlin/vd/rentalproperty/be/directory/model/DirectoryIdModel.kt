package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

inline class DirectoryIdModel(val id: String) {
  companion object {
    val NONE = DirectoryIdModel("")
  }
}
