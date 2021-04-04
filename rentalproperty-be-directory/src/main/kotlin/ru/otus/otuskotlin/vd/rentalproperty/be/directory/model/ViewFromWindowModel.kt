package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class ViewFromWindowModel(
  override val id: DirectoryIdModel = DirectoryIdModel.NONE,
  override val name: String = "",
) : DirectoryModel() {
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
      DirectoryIdModel("test-vfw-id"),
      "PARK"
    )
  }
}
