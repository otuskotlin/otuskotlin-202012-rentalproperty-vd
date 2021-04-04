package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory

import kotlinx.serialization.Serializable

@Serializable
data class BathroomTypeDto(
  override val id: String? = null,
  override val name: String? = null
) : IDirectoryTypeDto {
  companion object {
    val STUB_COMBINED = BathroomTypeDto(
      "test-brt-id",
      "COMBINED"
    )
  }
}
