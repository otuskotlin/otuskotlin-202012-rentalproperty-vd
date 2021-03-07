package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.media

import kotlinx.serialization.Serializable

@Serializable
data class MediaFileDto(
  val id: String? = null,
  val title: String? = null,
  val url: String? = null,
  val fileNameInStorage: String? = null,
)