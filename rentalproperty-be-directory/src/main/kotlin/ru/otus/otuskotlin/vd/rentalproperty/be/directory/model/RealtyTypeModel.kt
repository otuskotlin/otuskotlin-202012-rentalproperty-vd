package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class RealtyTypeModel(
  override val id: DirectoryIdModel = DirectoryIdModel.NONE,
  override val name: String = "",
) : DirectoryModel() {
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
