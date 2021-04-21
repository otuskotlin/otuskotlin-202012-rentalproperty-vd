package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class InfrastructureModel(
  override val id: DirectoryItemIdModel = DirectoryItemIdModel.NONE,
  override val name: String = "",
) : IDirectoryItemModel {
  companion object {
    val NONE = InfrastructureModel()
    val list = setOf(
      "GAZ",
      "HEATING",
      "ELECTRICITY",
      "SEWERAGE",  //Канализация
      "WATER"
    )
    val STUB_GAZ = InfrastructureModel(
      DirectoryItemIdModel("test-im-id"),
      "GAZ"
    )
    val STUB_ELECTRICITY = InfrastructureModel(
      DirectoryItemIdModel("test-im-id"),
      "ELECTRICITY"
    )
    val STUB_SEWERAGE = InfrastructureModel(
      DirectoryItemIdModel("test-im-id"),
      "SEWERAGE"
    )
    val STUB_WATER = InfrastructureModel(
      DirectoryItemIdModel("test-im-id"),
      "WATER"
    )
  }
}
