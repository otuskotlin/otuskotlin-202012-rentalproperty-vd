package ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums

import kotlinx.serialization.Serializable

/**
 * Список видов основного материала из которого сделан дом
 */
@Serializable
enum class HouseMaterialEnum {
  NONE,
  BLOCK,
  BRICK,
  BRICK_MONOLITHIC,
  MONOLITHIC,
  PANEL,
}
