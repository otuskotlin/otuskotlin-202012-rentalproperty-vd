package ru.otus.otuskotlin.vd.rentalproperty.be.repository.postgresql.schema

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileModel
import java.util.*

class MediaFileDto(id: EntityID<UUID>) : UUIDEntity(id) {
  var title by MediaFilesTable.title
  var url by MediaFilesTable.url
  var fileNameInStorage by MediaFilesTable.fileNameInStorage

  fun toModel() = MediaFileModel(
    id = MediaFileIdModel(id.value.toString()),
    title = title,
    url = url,
    fileNameInStorage = fileNameInStorage,
  )

  fun of(model: MediaFileModel) {
    title = model.title
    url = model.url
    fileNameInStorage = model.fileNameInStorage
  }

  companion object : UUIDEntityClass<MediaFileDto>(DirectoriesTable) {
  }
}
