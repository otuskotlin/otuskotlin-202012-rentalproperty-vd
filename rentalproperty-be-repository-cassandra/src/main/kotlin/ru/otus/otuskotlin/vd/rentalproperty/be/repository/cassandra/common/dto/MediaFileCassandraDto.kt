package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common.dto

import com.datastax.oss.driver.api.mapper.annotations.CqlName
import com.datastax.oss.driver.api.mapper.annotations.Entity
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileModel

@Entity
data class MediaFileCassandraDto(
  @CqlName(ID)
  val id: String? = null,
  @CqlName(TITLE)
  val title: String? = null,
  @CqlName(URL)
  val url: String? = null,
  @CqlName(FILENAME)
  val fileName: String? = null,
) {
  fun toModel() = MediaFileModel(
    id = id?.let { MediaFileIdModel(it) } ?: MediaFileModel.NONE.id,
    title = title ?: MediaFileModel.NONE.title,
    url = url ?: MediaFileModel.NONE.url,
    fileNameInStorage = fileName ?: MediaFileModel.NONE.fileNameInStorage,
  )

  companion object {
    const val TYPE_NAME = "media_file"
    const val ID = "id"
    const val TITLE = "title"
    const val URL = "url"
    const val FILENAME = "fileName"

    fun of(model: MediaFileModel) = MediaFileCassandraDto(
      id = model.id.takeIf { it != MediaFileModel.NONE.id }?.id,
      title = model.title.takeIf { it != MediaFileModel.NONE.title },
      url = model.url.takeIf { it != MediaFileModel.NONE.url },
      fileName = model.fileNameInStorage.takeIf { it != MediaFileModel.NONE.fileNameInStorage },
    )
  }
}
