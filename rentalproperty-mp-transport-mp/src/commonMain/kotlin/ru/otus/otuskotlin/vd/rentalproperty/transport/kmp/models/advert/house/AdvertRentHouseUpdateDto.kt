package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.advert.house

import kotlinx.serialization.Serializable

@Serializable
data class AdvertRentHouseUpdateDto(
  val id: String? = null,
  val userId: String? = null,
  val houseId: String? = null,
  val name: String? = null,
  val description: String? = null,
  val price: Double? = null,
  val published: String? = null,
)