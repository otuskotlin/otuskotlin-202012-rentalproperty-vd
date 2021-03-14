package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common

import kotlinx.serialization.Serializable

@Serializable
enum class ResponseStatusDto {
  SUCCESS,
  BAD_REQUEST,
  INTERNAL_SERVER_ERROR,
  NOT_FOUND,
}
