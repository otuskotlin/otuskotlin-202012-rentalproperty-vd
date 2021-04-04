package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory

import kotlinx.serialization.Serializable

@Serializable
data class HouseTypeDto(
  override val id: String? = null,
  override val name: String? = null
) : IDirectoryTypeDto {
  companion object {
    val STUB_SINGLE_HOUSE = HouseTypeDto(
      "test-ht-id",
      "SINGLE_HOUSE"
    )
    val STUB_MULTI_APARTMENT = HouseTypeDto(
      "test-ht-id",
      "MULTI_APARTMENT"
    )
  }
}
