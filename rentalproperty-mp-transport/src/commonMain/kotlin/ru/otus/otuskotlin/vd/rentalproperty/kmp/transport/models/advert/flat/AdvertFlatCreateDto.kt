package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.flat

import kotlinx.serialization.Serializable

@Serializable
data class AdvertFlatCreateDto(
  val userId: String? = null,
  val flatId: String? = null,
  val name: String? = null,
  val description: String? = null,
  val price: Double? = null,
  val startDate: String? = null,
  val period: Int? = null,
  val published: String? = null,
)