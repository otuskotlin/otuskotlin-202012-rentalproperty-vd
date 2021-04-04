package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class PlotStatusModel(
  override val id: DirectoryIdModel = DirectoryIdModel.NONE,
  override val name: String = "",
) : DirectoryModel() {
  companion object {
    val NONE = PlotStatusModel()
    val list = setOf(
      "IRP",   //individual residential property
      "GARDEN",
      "FARM"
    )
    val STUB_IRP = PlotStatusModel(
      DirectoryIdModel("test-ps-id"),
      "IRP"
    )
  }
}
