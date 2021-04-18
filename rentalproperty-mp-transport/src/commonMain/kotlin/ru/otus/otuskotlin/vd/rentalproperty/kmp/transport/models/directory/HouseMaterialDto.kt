package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory

import kotlinx.serialization.Serializable

@Serializable
data class HouseMaterialDto(
  override val id: String? = null,
  override val name: String? = null
) : IDirectoryDto, DirectoryDto() {
  companion object {
    val STUB_BRICK = HouseMaterialDto(
      "test-hm-id",
      "BRICK"
    )
    val STUB_PANEL = HouseMaterialDto(
      "test-hm-id",
      "PANEL"
    )
  }
}
