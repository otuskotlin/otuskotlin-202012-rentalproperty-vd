package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

/**
 * Список бытовой техники и устройств в доме или квартире
 */
data class AppliancesModel(
  override val id: DirectoryItemIdModel = DirectoryItemIdModel.NONE,
  override val name: String = "",
) : IDirectoryItemModel {
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
      DirectoryItemIdModel("test-a-id-1"),
      "AIR_CONDITIONER"
    )
    val STUB_KITCHEN_STOVE = AppliancesModel(
      DirectoryItemIdModel("test-a-id-2"),
      "KITCHEN_STOVE"
    )
    val STUB_INTERNET = AppliancesModel(
      DirectoryItemIdModel("test-a-id-3"),
      "INTERNET"
    )
  }
}
