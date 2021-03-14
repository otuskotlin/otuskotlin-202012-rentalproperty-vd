package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.response

import org.springframework.web.multipart.MultipartFile
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.MediaFileTypeEnum

data class MediaFileRequest(
  val title: String,
  val type: MediaFileTypeEnum,
  val file: MultipartFile
)
