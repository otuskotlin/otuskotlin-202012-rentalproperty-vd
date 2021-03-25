package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

/**
 * Список удобств в квартире или в доме
 */
data class ConvenienceTypeModel(
  override val id: DirectoryIdModel = DirectoryIdModel.NONE,
  override val name: String = "",
) : DirectoryModel() {
  companion object {
    val NONE = ConvenienceTypeModel()
    val list = setOf(
      "AIR_CONDITIONER",
      "CONCIERGE",
      "DISHWASHER",
      "FRIDGE",
      "INTERCOM",
      "INTERNET",
      "KINDERGARTEN",
      "KITCHEN_STOVE",
      "FURNITURE_IN_KITCHEN",
      "FURNITURE_IN_ROOM",
      "PARK",
      "PARKING",
      "PLAYGROUND",
      "REFRIGERATOR",
      "SCHOOL",
      "SHOPPING_CENTER",
      "SPORTS_GROUND",
      "TELEPHONE",
      "TV",
      "WASHING_MACHINE"
    )
  }
}
