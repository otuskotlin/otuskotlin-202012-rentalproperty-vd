package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory

import kotlinx.serialization.Serializable

@Serializable
data class AppliancesDto(
  override val id: String? = null,
  override val name: String? = null
) : IDirectoryTypeDto {
  companion object {
    val STUB_AIR_CONDITIONER = AppliancesDto(
      "test-ct-id",
      "AIR_CONDITIONER"
    )
    val STUB_KITCHEN_STOVE = AppliancesDto(
      "test-ct-id",
      "KITCHEN_STOVE"
    )
    val STUB_INTERNET = AppliancesDto(
      "test-ct-id",
      "INTERNET"
    )
  }
}
