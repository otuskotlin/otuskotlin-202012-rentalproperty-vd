package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory

import kotlinx.serialization.Serializable

@Serializable
data class InfrastructureDto(
  override val id: String? = null,
  override val name: String? = null
) : IDirectoryDto {
  companion object {
    val STUB_GAZ = InfrastructureDto(
      "test-i-id",
      "GAZ"
    )
    val STUB_ELECTRICITY = InfrastructureDto(
      "test-i-id",
      "ELECTRICITY"
    )
    val STUB_SEWERAGE = InfrastructureDto(
      "test-i-id",
      "SEWERAGE"
    )
    val STUB_WATER = InfrastructureDto(
      "test-i-id",
      "WATER"
    )
  }
}
