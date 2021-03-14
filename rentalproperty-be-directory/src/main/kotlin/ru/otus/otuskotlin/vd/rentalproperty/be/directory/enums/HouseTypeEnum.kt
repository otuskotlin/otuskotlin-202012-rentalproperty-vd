package ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums

import kotlinx.serialization.Serializable

/**
 * Типы домов
 */
@Serializable
enum class HouseTypeEnum {
  NONE,
  SINGLE_HOUSE,
  PART_HOUSE,
  TOWNHOUSE,
  DUPLEX,
}
