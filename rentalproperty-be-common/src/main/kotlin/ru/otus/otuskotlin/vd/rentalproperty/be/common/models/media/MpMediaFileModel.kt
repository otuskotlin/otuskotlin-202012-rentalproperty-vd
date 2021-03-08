package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media

data class MpMediaFileModel(
  val id: MpMediaFileIdModel = MpMediaFileIdModel.NONE,
  val title: String = "",
  val url: String = "",
  val fileNameInStorage: String = "",
) {
  companion object {
    val NONE = MpMediaFileModel()
  }
}