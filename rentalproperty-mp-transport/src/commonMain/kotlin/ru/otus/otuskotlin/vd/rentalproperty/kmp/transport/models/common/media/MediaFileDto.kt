package media

import kotlinx.serialization.Serializable

/**
 * Media file content
 * Example: avatar, photo, docs
 */
@Serializable
data class MediaFileDto(
  val id: String? = null,
  val title: String? = null,
  val url: String? = null,
  val fileNameInStorage: String? = null,
)