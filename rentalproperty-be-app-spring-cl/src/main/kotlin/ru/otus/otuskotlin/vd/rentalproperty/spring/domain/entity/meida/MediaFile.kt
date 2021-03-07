package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.meida

data class MediaFile(
  var id: Long,
  var title: String,
  var url: String,
  var fileNameInStorage: String
)