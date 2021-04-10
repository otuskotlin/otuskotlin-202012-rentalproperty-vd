package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IFilterItem

@Serializable
data class HouseListFilterDto(
  override val text: String? = null,
) : IFilterItem
