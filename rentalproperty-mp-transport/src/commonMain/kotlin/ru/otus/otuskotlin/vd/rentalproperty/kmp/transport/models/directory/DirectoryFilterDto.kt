package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IFilterItem

@Serializable
data class DirectoryFilterDto(
  override val text: String? = null,
  val directoryItem: IDirectoryDto? = null,
) : IFilterItem
