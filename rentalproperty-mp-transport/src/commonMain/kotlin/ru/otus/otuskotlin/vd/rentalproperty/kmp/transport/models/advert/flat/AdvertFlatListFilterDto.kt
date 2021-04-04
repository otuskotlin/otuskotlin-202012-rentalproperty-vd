package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.flat

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IFilterItem

@Serializable
data class AdvertFlatListFilterDto(
  override val text: String? = null,
) : IFilterItem
