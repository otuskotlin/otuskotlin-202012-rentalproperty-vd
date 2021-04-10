package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class InfrastructureModel(
  override val id: DirectoryIdModel = DirectoryIdModel.NONE,
  override val name: String = "",
) : DirectoryModel() {
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
      DirectoryIdModel("test-im-id"),
      "GAZ"
    )
    val STUB_ELECTRICITY = InfrastructureModel(
      DirectoryIdModel("test-im-id"),
      "ELECTRICITY"
    )
    val STUB_SEWERAGE = InfrastructureModel(
      DirectoryIdModel("test-im-id"),
      "SEWERAGE"
    )
    val STUB_WATER = InfrastructureModel(
      DirectoryIdModel("test-im-id"),
      "WATER"
    )
  }
}
