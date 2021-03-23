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
  }
}
