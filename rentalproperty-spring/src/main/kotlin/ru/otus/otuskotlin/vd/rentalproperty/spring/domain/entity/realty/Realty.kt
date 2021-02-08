package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.realty

import ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model.Id
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.RealtyTypeEnum

abstract class Realty {
  abstract var id: Id
  abstract var realtyType: RealtyTypeEnum
  abstract var price: Double
  abstract var area: Double
  abstract var address: String
}