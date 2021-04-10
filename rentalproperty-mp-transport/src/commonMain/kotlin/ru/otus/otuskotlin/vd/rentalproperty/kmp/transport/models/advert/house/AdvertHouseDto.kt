package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ItemPermission

@Serializable
data class AdvertHouseDto(
  val id: String? = null,
  val permissions: Set<ItemPermission>? = null,
  val userId: String? = null,
  val houseId: String? = null,
  val name: String? = null,
  val description: String? = null,
  val price: Double? = null,
  val published: String? = null,
)