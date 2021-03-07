package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.IMpFilterItem

@Serializable
data class MpHouseListFilterDto(
  override val text: String? = null,
) : IMpFilterItem
