package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common

import kotlinx.serialization.Serializable

@Serializable
class IdModel(val id: String) {
  companion object {
    val NONE = IdModel("")
  }
}