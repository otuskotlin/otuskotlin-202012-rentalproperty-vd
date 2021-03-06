package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house

import kotlinx.serialization.Serializable

@Serializable
data class AdvertHouseUpdateDto(
  val id: String? = null,
  val userId: String? = null,
  val houseId: String? = null,
  val name: String? = null,
  val description: String? = null,
  val price: Double? = null,
  val published: String? = null,
) {
  companion object {
    val STUB = AdvertHouseUpdateDto(
      id = "test-advert-id",
      userId = "test-user-id",
      houseId = "test-house-id",
      name = "Продаётся дом",
      description = "Хороший дом",
      price = 1_500_000.0,
    )
  }
}