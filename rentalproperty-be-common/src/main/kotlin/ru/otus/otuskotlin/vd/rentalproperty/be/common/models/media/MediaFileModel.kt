package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media

data class MediaFileModel(
  val id: MediaFileIdModel = MediaFileIdModel.NONE,
  val title: String = "",
  val url: String = "",
  val fileNameInStorage: String = "",
) {
  companion object {
    val NONE = MediaFileModel()
  }
}