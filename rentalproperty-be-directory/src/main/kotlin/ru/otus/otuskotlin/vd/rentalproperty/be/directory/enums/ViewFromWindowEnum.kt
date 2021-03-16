package ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums

import kotlinx.serialization.Serializable

/**
 * Возможные виды из окна квартиры
 */
@Serializable
enum class ViewFromWindowEnum {
  FOREST,
  PARK,
  POND, //водоём/пруд
  STREET,
  YARD  //двор
}
