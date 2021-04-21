package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class ViewFromWindowModel(
  override val id: DirectoryItemIdModel = DirectoryItemIdModel.NONE,
  override val name: String = "",
) : IDirectoryItemModel {
  companion object {
    val NONE = ViewFromWindowModel()
    val list = setOf(
      "FOREST",
      "PARK",
      "POND", //водоём/пруд
      "STREET",
      "YARD"  //двор
    )
    val STUB_PARK = ViewFromWindowModel(
      DirectoryItemIdModel("test-vfw-id"),
      "PARK"
    )
  }
}
