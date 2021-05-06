package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class HouseMaterialModel(
  override val id: DirectoryItemIdModel = DirectoryItemIdModel.NONE,
  override val name: String = "",
) : IDirectoryItemModel {
  companion object {
    val NONE = HouseMaterialModel()
    val list = listOf(
      "BLOCK",
      "BRICK",
      "BRICK_MONOLITHIC",
      "MONOLITHIC",
      "PANEL",
    )
    val STUB_BRICK = HouseMaterialModel(
      DirectoryItemIdModel("test-hm-id"),
      "BRICK"
    )
    val STUB_PANEL = HouseMaterialModel(
      DirectoryItemIdModel("test-hm-id"),
      "PANEL"
    )
  }
}