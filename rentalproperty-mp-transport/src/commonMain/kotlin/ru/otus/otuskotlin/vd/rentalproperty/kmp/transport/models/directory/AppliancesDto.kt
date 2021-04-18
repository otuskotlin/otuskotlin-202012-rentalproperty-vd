package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory

import kotlinx.serialization.Serializable

@Serializable
data class AppliancesDto(
  override val id: String? = null,
  override val name: String? = null
) : IDirectoryDto, DirectoryDto() {
  companion object {
    val STUB_AIR_CONDITIONER = AppliancesDto(
      "test-ap-id",
      "AIR_CONDITIONER"
    )
    val STUB_KITCHEN_STOVE = AppliancesDto(
      "test-ap-id",
      "KITCHEN_STOVE"
    )
    val STUB_INTERNET = AppliancesDto(
      "test-ap-id",
      "INTERNET"
    )
  }
}
