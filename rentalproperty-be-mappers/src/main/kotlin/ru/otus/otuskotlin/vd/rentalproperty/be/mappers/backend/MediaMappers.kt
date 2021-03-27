package ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileModel

internal fun MediaFileModel.toTransport() =
  media.MediaFileDto(
    id = id.id.takeIf { it.isNotBlank() },
    title = title.takeIf { it.isNotBlank() },
    url = url.takeIf { it.isNotBlank() },
    fileNameInStorage = fileNameInStorage.takeIf { it.isNotBlank() },
  )

internal fun media.MediaFileDto.toModel() = MediaFileModel(
  id = id?.let { MediaFileIdModel(it) }
    ?: MediaFileIdModel.NONE,
  title = title ?: "",
  url = url ?: "",
  fileNameInStorage = fileNameInStorage ?: "",
)