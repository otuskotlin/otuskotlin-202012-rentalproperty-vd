package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

/**
 * Список удобств в квартире или доме
 */
data class ConvenienceModel(
  override val id: DirectoryItemIdModel = DirectoryItemIdModel.NONE,
  override val name: String = "",
) : IDirectoryItemModel {
  companion object {
    val NONE = ConvenienceModel()
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
    val STUB_GAS = ConvenienceModel(
      DirectoryItemIdModel("test-ct-id-1"),
      "GAS"
    )
    val STUB_FURNITURE_IN_KITCHEN = ConvenienceModel(
      DirectoryItemIdModel("test-ct-id-2"),
      "FURNITURE_IN_KITCHEN"
    )
    val STUB_PARKING = ConvenienceModel(
      DirectoryItemIdModel("test-ct-id-3"),
      "PARKING"
    )
  }
}
