package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.realty

import ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model.Id
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.ConvenienceEnum
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.RealtyTypeEnum

class Room(
  override var id: Id = Id.NONE,
  override var realtyType: RealtyTypeEnum = RealtyTypeEnum.ROOM,
  override var price: Double,
  override var area: Double,
  override var address: String,
  var number: Int = 1,
  var ceilingHeight: Double,  //высота потолков
  var conveniences: MutableSet<ConvenienceEnum> = mutableSetOf(),
  var house: House
) : Realty()