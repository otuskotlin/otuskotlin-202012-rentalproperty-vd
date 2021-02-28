package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.media

data class MediaFileDto(
  var id: Long,
  var title: String,
  var url: String,
  var fileNameInStorage: String
)