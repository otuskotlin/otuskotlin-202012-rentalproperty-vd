package ru.otus.otuskotlin.vd.rentalproperty.ktor.model.realty

import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.ConvenienceEnum
import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.RealtyTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.ktor.model.Id

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