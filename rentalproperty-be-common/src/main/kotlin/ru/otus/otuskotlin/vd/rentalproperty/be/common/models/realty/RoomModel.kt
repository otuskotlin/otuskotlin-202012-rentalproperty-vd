package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.ConvenienceEnum
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.RealtyTypeEnum

class RoomModel(
  val id: RoomIdModel = RoomIdModel.NONE,
  val realtyType: RealtyTypeEnum = RealtyTypeEnum.ROOM,
  val price: Double,
  val area: Double,
  val address: String,
  val houseId: HouseIdModel,
  val number: Int = 1,
  val ceilingHeight: Double? = null,
  val conveniences: MutableSet<ConvenienceEnum> = mutableSetOf(),
)