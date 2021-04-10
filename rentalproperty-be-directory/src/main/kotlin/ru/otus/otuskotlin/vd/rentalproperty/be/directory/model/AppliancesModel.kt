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
    val STUB_AIR_CONDITIONER = AppliancesModel(
      DirectoryIdModel("test-a-id"),
      "AIR_CONDITIONER"
    )
    val STUB_KITCHEN_STOVE = AppliancesModel(
      DirectoryIdModel("test-a-id"),
      "KITCHEN_STOVE"
    )
    val STUB_INTERNET = AppliancesModel(
      DirectoryIdModel("test-a-id"),
      "INTERNET"
    )
  }
}
