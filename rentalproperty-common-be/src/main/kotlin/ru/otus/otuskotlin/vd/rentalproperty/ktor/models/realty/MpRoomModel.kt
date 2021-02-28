package ru.otus.otuskotlin.vd.rentalproperty.ktor.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.ConvenienceEnum
import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.RealtyTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.ktor.models.IMpIdModel

class MpRoomModel(
  override val id: MpRoomIdModel = MpRoomIdModel.NONE,
  override val realtyType: RealtyTypeEnum = RealtyTypeEnum.ROOM,
  override val price: Double,
  override val area: Double,
  override val address: String,
  override val houseId: IMpIdModel,
  override val number: Int = 1,
  override val ceilingHeight: Double? = null,
  override val conveniences: MutableSet<ConvenienceEnum> = mutableSetOf(),
) : IMpRealtyModel, IMpRoomModel