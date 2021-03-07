package ru.otus.otuskotlin.vd.rentalproperty.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MpMediaFileIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MpMediaFileModel
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.media.MediaFileDto

internal fun MpMediaFileModel.toTransport() = MediaFileDto(
  id = id.id.takeIf { it.isNotBlank() },
  title = title.takeIf { it.isNotBlank() },
  url = url.takeIf { it.isNotBlank() },
  fileNameInStorage = fileNameInStorage.takeIf { it.isNotBlank() },
)

internal fun MediaFileDto.toModel() = MpMediaFileModel(
  id = id?.let { MpMediaFileIdModel(it) }
    ?: MpMediaFileIdModel.NONE,
  title = title ?: "",
  url = url ?: "",
  fileNameInStorage = fileNameInStorage ?: "",
)