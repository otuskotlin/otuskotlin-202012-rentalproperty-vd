package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class PlotStatusModel(
  override val id: DirectoryItemIdModel = DirectoryItemIdModel.NONE,
  override val name: String = "",
) : IDirectoryItemModel {
  companion object {
    val NONE = PlotStatusModel()
    val list = setOf(
      "IRP",   //individual residential property
      "GARDEN",
      "FARM"
    )
    val STUB_IRP = PlotStatusModel(
      DirectoryItemIdModel("test-ps-id-1"),
      "IRP"
    )
  }
}
