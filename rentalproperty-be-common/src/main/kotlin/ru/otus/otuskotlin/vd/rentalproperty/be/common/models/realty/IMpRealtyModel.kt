package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.RealtyTypeEnum

interface IMpRealtyModel {
  val id: ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IMpIdModel
  val realtyType: RealtyTypeEnum
  val price: Double
  val area: Double
  val address: String
}
