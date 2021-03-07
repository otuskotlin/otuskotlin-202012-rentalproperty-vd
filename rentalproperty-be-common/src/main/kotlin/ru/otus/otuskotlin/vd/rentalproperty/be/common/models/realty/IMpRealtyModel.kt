package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.RealtyTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IMpIdModel

interface IMpRealtyModel {
  val id: IMpIdModel
  val realtyType: RealtyTypeEnum
  val price: Double
  val area: Double
  val address: String
}
