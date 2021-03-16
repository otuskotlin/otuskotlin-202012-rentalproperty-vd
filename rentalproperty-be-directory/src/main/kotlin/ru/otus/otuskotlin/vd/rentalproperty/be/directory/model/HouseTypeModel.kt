package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class HouseTypeModel(
  override val id: DirectoryIdModel = DirectoryIdModel.NONE,
  override val name: String = "",
) : DirectoryModel() {
  companion object {
    val NONE = HouseTypeModel()
    val list = setOf(
      "COMBINED",   //совмещённый
      "SEPARATED",  //раздельный
    )
  }
}