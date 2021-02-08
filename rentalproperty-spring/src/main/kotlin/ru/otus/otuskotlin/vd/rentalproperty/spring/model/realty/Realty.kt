package ru.otus.otuskotlin.vd.rentalproperty.spring.model.realty

import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.RealtyTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.spring.model.Id

abstract class Realty {
  abstract var id: Id
  abstract var realtyType: RealtyTypeEnum
  abstract var price: Double
  abstract var area: Double
  abstract var address: String
}