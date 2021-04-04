package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IFilterItem

@Serializable
data class AdvertHouseListFilterDto(
  override val text: String? = null,
) : IFilterItem
