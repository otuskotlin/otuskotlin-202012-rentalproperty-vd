package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IFilterItem

@Serializable
data class FlatListFilterDto(
  override val text: String? = null,
) : IFilterItem
