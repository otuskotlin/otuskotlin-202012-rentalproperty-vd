package ru.otus.otuskotlin.vd.rentalproperty.spring.model.realty

import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.ConvenienceEnum
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.RealtyTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.spring.model.Id

data class Flat(
  override var id: Id = Id.NONE,
  override var realtyType: RealtyTypeEnum = RealtyTypeEnum.FLAT,
  override var price: Double,
  override var area: Double,
  override var address: String,
  var rooms: Int,
  var ceilingHeight: Double,  //высота потолков
  var conveniences: MutableSet<ConvenienceEnum> = mutableSetOf(),
  var house: House
) : Realty()