package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class RealtyTypeModel(
  override val id: DirectoryItemIdModel = DirectoryItemIdModel.NONE,
  override val name: String = "",
) : IDirectoryItemModel {
  companion object {
    val NONE = RealtyTypeModel()
    val list = setOf(
      "APARTMENT",
      "CAR_BOX",
      "COMMERCIAL",
      "GARAGE",
      "FLAT",
      "HOTEL",
      "HOSTEL",
      "HOUSE",
      "NEW_BUILDING",
      "OFFICE",
      "PARKING_SPACE",
      "PART_HOUSE",
      "PLOT",
      "ROOM",
    )
  }
}
