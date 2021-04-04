package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

/**
 * Список бытовой техники и устройств в доме или квартире
 */
data class AppliancesModel(
  override val id: DirectoryIdModel = DirectoryIdModel.NONE,
  override val name: String = "",
) : DirectoryModel() {
  companion object {
    val NONE = AppliancesModel()
    val list = setOf(
      "AIR_CONDITIONER",
      "DISHWASHER",
      "FRIDGE",
      "INTERCOM",
      "INTERNET",
      "KITCHEN_STOVE",
      "MICROWAVE",
      "TELEPHONE",
      "TV",
      "WASHING_MACHINE"
    )
  }
}
