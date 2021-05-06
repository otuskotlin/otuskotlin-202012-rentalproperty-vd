package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory

import kotlinx.serialization.Serializable

@Serializable
data class DirectoryItemDto(
  override val id: String? = null,
  override val name: String? = null,
) : IDirectoryDto, DirectoryDto() {
  companion object {
    val STUB = DirectoryItemDto(
      "test-dir-id",
      "name item"
    )
  }
}


