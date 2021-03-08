package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.response

import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.RealtyTypeEnum

data class RealtyFilterRequest(
  val realtyType: RealtyTypeEnum,
  val priceStart: Double,
  val priceEnd: Double
)
