package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media

data class MediaFile(
  var id: Long,
  var title: String,
  var url: String,
  var fileNameInStorage: String
)