package ru.otus.otuskotlin.vd.rentalproperty.ktor.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.RealtyTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.ktor.models.IMpIdModel

interface IMpRealtyModel {
  val id: IMpIdModel
  val realtyType: RealtyTypeEnum
  val price: Double
  val area: Double
  val address: String
}
