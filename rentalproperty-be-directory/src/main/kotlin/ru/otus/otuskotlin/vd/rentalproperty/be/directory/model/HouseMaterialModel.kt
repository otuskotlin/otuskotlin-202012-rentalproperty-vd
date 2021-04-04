package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class HouseMaterialModel(
  override val id: DirectoryIdModel = DirectoryIdModel.NONE,
  override val name: String = "",
) : DirectoryModel() {
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
      DirectoryIdModel("test-hm-id"),
      "BRICK"
    )
    val STUB_PANEL = HouseMaterialModel(
      DirectoryIdModel("test-hm-id"),
      "PANEL"
    )
  }
}