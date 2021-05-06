package ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.common

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileModel

data class MediaFileInMemoryDto(
  val id: String? = null,
  val title: String? = null,
  val url: String? = null,
  val fileNameInStorage: String? = null,
) {
  fun toModel() = MediaFileModel(
    id = id?.let { MediaFileIdModel(it) } ?: MediaFileIdModel.NONE,
    title = title ?: "",
    url = url ?: "",
    fileNameInStorage = fileNameInStorage ?: "",
  )

  companion object {
    fun of(model: MediaFileModel) = of(model, model.id.id)

    fun of(model: MediaFileModel, id: String) = MediaFileInMemoryDto(
      id = id.takeIf { it.isNotBlank() },
      title = model.title.takeIf { it.isNotBlank() },
      url = model.url.takeIf { it.isNotBlank() },
      fileNameInStorage = model.fileNameInStorage.takeIf { it.isNotBlank() },
    )
  }
}
