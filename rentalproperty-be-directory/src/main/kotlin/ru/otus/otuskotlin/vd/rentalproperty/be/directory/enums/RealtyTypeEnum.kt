package ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums

import kotlinx.serialization.Serializable

/**
 * Список вариантов недвижимого имущества
 */
@Serializable
enum class RealtyTypeEnum {
  APARTMENT,
  CAR_BOX,
  COMMERCIAL,
  GARAGE,
  FLAT,
  HOTEL,
  HOSTEL,
  HOUSE,
  NEW_BUILDING,
  OFFICE,
  PARKING_SPACE,
  PART_HOUSE,
  PLOT,
  ROOM,
}
