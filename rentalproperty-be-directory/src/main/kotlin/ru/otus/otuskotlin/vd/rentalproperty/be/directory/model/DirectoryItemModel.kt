package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class DirectoryItemModel(
  override val id: DirectoryItemIdModel = DirectoryItemIdModel.NONE,
  override val name: String = "",
) : IDirectoryItemModel {
  companion object {
    val NONE = DirectoryItemModel()
    val STUB = DirectoryItemModel(
      DirectoryItemIdModel("test-dir-id"),
      "name item"
    )
  }
}
