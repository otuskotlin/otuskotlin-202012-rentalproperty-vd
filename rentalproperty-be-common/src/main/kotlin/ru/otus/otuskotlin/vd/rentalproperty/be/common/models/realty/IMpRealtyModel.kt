package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

interface IMpRealtyModel {
  val id: ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IMpIdModel
  val realtyType: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.RealtyTypeEnum
  val price: Double
  val area: Double
  val address: String
}
