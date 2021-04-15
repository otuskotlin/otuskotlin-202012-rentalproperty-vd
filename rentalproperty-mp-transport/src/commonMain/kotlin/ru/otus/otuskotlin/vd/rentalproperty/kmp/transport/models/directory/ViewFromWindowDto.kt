package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory

import kotlinx.serialization.Serializable

@Serializable
data class ViewFromWindowDto(
  override val id: String? = null,
  override val name: String? = null
) : IDirectoryDto {
  companion object {
    val STUB_PARK = ViewFromWindowDto(
      "test-vfw-id",
      "PARK"
    )
  }
}
