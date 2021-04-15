package ru.otus.otuskotlin.vd.rentalproperty.be.directory.model

data class RepairTypeModel(
  override val id: DirectoryItemIdModel = DirectoryItemIdModel.NONE,
  override val name: String = "",
) : IDirectoryItemModel {
  companion object {
    val NONE = RepairTypeModel()
    val list = setOf(
      "WITHOUT_REPAIR",
      "RENOVATION",           //евроремонт
      "DESIGNER_RENOVATION",  //дизайнерский ремонт
      "REDECORATING",         //косметический ремонт
    )
    val STUB_RENOVATION = RepairTypeModel(
      DirectoryItemIdModel("test-rt-id"),
      "RENOVATION"
    )
  }
}
