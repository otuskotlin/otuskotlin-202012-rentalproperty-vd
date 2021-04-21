package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class BathroomTypeModel(
  override val id: DirectoryItemIdModel = DirectoryItemIdModel.NONE,
  override val name: String = "",
) : IDirectoryItemModel {
  companion object {
    val NONE = BathroomTypeModel()
    val list = setOf(
      "COMBINED",   //совмещённый
      "SEPARATED",  //раздельный
    )
    val STUB_COMBINED = BathroomTypeModel(
      DirectoryItemIdModel("test-brt-id"),
      "COMBINED"
    )
  }
}
