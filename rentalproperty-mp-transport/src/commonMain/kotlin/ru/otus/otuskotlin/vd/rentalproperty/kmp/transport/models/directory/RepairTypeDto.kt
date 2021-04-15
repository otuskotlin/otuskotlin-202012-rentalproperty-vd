package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory

import kotlinx.serialization.Serializable

@Serializable
data class RepairTypeDto(
  override val id: String? = null,
  override val name: String? = null
) : IDirectoryDto {
  companion object {
    val STUB_RENOVATION = RepairTypeDto(
      "test-rt-id",
      "RENOVATION"
    )
  }
}
