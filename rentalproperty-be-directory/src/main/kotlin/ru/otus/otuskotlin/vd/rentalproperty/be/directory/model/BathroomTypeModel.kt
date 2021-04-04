package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class BathroomTypeModel(
  override val id: DirectoryIdModel = DirectoryIdModel.NONE,
  override val name: String = "",
) : DirectoryModel() {
  companion object {
    val NONE = BathroomTypeModel()
    val list = setOf(
      "COMBINED",   //совмещённый
      "SEPARATED",  //раздельный
    )
    val STUB_COMBINED = BathroomTypeModel(
      DirectoryIdModel("test-brt-id"),
      "COMBINED"
    )
  }
}
