package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.advert

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ItemPermission

@Serializable
data class AdvertRentFlatDto(
  val id: String? = null,
  val permissions: Set<ItemPermission>? = null,
  val userId: String? = null,
  val flatId: String? = null,
  val name: String? = null,
  val description: String? = null,
  val price: Double? = null,
  val published: String? = null,
)