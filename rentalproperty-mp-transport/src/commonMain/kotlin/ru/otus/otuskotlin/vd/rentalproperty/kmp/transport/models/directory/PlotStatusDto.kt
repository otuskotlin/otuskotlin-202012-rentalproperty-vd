package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory

import kotlinx.serialization.Serializable

@Serializable
data class PlotStatusDto(
  override val id: String? = null,
  override val name: String? = null
) : IDirectoryTypeDto
