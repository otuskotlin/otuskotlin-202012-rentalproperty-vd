package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

/**
 * Список удобств в квартире или доме
 */
data class ConvenienceTypeModel(
  override val id: DirectoryIdModel = DirectoryIdModel.NONE,
  override val name: String = "",
) : DirectoryModel() {
  companion object {
    val NONE = ConvenienceTypeModel()
    val list = setOf(
      "CONCIERGE",
      "GAS",
      "KINDERGARTEN",
      "FURNITURE_IN_KITCHEN",
      "FURNITURE_IN_ROOM",
      "PARK",
      "PARKING",
      "PLAYGROUND",
      "SCHOOL",
      "SHOPPING_CENTER",
      "SPORTS_GROUND",
    )
    val STUB_GAS = ConvenienceTypeModel(
      DirectoryIdModel("test-ct-id"),
      "GAS"
    )
    val STUB_FURNITURE_IN_KITCHEN = ConvenienceTypeModel(
      DirectoryIdModel("test-ct-id"),
      "FURNITURE_IN_KITCHEN"
    )
    val STUB_PARKING = ConvenienceTypeModel(
      DirectoryIdModel("test-ct-id"),
      "PARKING"
    )
  }
}
