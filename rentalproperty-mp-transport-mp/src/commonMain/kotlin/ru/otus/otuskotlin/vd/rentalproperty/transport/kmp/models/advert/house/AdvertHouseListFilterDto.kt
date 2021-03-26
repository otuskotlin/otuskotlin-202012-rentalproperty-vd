package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.advert.house

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.IFilterItem

@Serializable
data class AdvertHouseListFilterDto(
  override val text: String? = null,
) : IFilterItem
