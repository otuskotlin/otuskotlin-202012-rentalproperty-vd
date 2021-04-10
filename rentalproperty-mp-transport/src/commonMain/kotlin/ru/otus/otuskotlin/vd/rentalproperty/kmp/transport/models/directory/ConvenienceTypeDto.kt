package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory

import kotlinx.serialization.Serializable

@Serializable
data class ConvenienceTypeDto(
  override val id: String? = null,
  override val name: String? = null
) : IDirectoryTypeDto {
  companion object {
    val STUB_GAS = ConvenienceTypeDto(
      "test-ct-id",
      "GAS"
    )
    val STUB_FURNITURE_IN_KITCHEN = ConvenienceTypeDto(
      "test-ct-id",
      "FURNITURE_IN_KITCHEN"
    )
    val STUB_PARKING = ConvenienceTypeDto(
      "test-ct-id",
      "PARKING"
    )
  }
}
