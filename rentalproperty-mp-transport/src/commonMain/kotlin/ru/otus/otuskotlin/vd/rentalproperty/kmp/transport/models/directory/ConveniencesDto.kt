package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory

import kotlinx.serialization.Serializable

@Serializable
data class ConveniencesDto(
  override val id: String? = null,
  override val name: String? = null
) : IDirectoryDto, DirectoryDto() {
  companion object {
    val STUB_GAS = ConveniencesDto(
      "test-ct-id",
      "GAS"
    )
    val STUB_FURNITURE_IN_KITCHEN = ConveniencesDto(
      "test-ct-id",
      "FURNITURE_IN_KITCHEN"
    )
    val STUB_PARKING = ConveniencesDto(
      "test-ct-id",
      "PARKING"
    )
  }
}
