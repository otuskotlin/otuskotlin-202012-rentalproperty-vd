package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.directory

import kotlinx.serialization.Serializable

@Serializable
data class HouseMaterialDto(
  override val id: String? = null,
  override val name: String? = null
) : IDirectoryTypeDto
