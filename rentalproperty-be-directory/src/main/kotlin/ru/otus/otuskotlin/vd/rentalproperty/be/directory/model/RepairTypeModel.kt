package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class RepairTypeModel(
  override val id: DirectoryIdModel = DirectoryIdModel.NONE,
  override val name: String = "",
) : DirectoryModel() {
  companion object {
    val NONE = RepairTypeModel()
    val list = setOf(
      "WITHOUT_REPAIR",
      "RENOVATION",           //евроремонт
      "DESIGNER_RENOVATION",  //дизайнерский ремонт
      "REDECORATING",         //косметический ремонт
    )
  }
}
