package ru.otus.otuskotlin.vd.rentalproperty.model.realty

import ru.otus.otuskotlin.vd.rentalproperty.enums.RealtyTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.model.Id

abstract class Realty {
  abstract var id: Id
  abstract var realtyType: RealtyTypeEnum
  abstract var price: Double
  abstract var area: Double
  abstract var address: String
}