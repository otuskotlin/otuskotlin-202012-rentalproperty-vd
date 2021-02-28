package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty

interface IMpRealtyCreateDto {
  val realtyType: RealtyTypeDto?
  val price: Double?
  val area: Double?
  val address: String?
}
