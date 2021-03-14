package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.IFilterItem

@Serializable
data class HouseListFilterDto(
  override val text: String? = null,
) : IFilterItem
