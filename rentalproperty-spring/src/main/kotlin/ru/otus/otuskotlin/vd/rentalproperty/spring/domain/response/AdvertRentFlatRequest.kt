package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.response

import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.realty.Flat
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.RealtyTypeEnum

data class AdvertRentFlatRequest(
  val realtyType: RealtyTypeEnum,
  val name: String,
  var flat: Flat
)
