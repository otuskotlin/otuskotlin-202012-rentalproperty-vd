package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class DirectoryFilterModel(
  val text: String = "",
  val includeDescription: Boolean = false,
  val sortBy: SortModel = SortModel.NONE,
  val offset: Int = Int.MIN_VALUE,
  val count: Int = Int.MIN_VALUE,
) {
  companion object {
    val NONE = DirectoryFilterModel()
  }
}
