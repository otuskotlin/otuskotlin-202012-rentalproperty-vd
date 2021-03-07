package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.ConvenienceEnum
import ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.RealtyTypeEnum

class MpRoomModel(
  override val id: MpRoomIdModel = MpRoomIdModel.NONE,
  override val realtyType: RealtyTypeEnum = RealtyTypeEnum.ROOM,
  override val price: Double,
  override val area: Double,
  override val address: String,
  override val houseId: ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IMpIdModel,
  override val number: Int = 1,
  override val ceilingHeight: Double? = null,
  override val conveniences: MutableSet<ConvenienceEnum> = mutableSetOf(),
) : IMpRealtyModel, IMpRoomModel