package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class HouseTypeModel(
  override val id: DirectoryItemIdModel = DirectoryItemIdModel.NONE,
  override val name: String = "",
) : IDirectoryItemModel {
  companion object {
    val NONE = HouseTypeModel()
    val list = setOf(
      "DUPLEX",
      "MULTI_APARTMENT",
      "SINGLE_HOUSE",
      "PART_HOUSE",
      "TOWNHOUSE",
    )
    val STUB_SINGLE_HOUSE = HouseTypeModel(
      DirectoryItemIdModel("test-ht-id-1"),
      "SINGLE_HOUSE"
    )
    val STUB_MULTI_APARTMENT = HouseTypeModel(
      DirectoryItemIdModel("test-ht-id-1"),
      "MULTI_APARTMENT"
    )
  }
}
