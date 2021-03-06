package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.flat

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ItemPermission

@Serializable
data class AdvertFlatDto(
  val id: String? = null,
  val permissions: Set<ItemPermission>? = null,
  val userId: String? = null,
  val flatId: String? = null,
  val name: String? = null,
  val description: String? = null,
  val price: Double? = null,
  val startDate: String? = null,
  val endDate: String? = null,
  val minPeriod: Int? = null,
  val published: String? = null,
)