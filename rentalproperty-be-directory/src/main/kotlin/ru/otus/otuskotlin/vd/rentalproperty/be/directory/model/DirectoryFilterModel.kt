package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class DirectoryFilterModel(
  val text: String = ""
) {
  companion object {
    val NONE = DirectoryFilterModel()
  }
}
