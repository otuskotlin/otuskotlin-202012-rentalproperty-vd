package ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums

import kotlinx.serialization.Serializable

/**
 * Инфраструктура в доме или квартире
 */
@Serializable
enum class InfrastructureEnum {
  GAZ,
  HEATING,
  ELECTRICITY,
  SEWERAGE,  //Канализация
  WATER
}
