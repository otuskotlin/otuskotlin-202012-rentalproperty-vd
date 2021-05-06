package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IFilterItem
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.SortDto

@Serializable
data class HouseFilterDto(
  override val text: String? = null,
  override val sortBy: SortDto? = null,
  override val offset: Int? = null,
  override val count: Int? = null,
  override val includeDescription: Boolean? = null,
) : IFilterItem
