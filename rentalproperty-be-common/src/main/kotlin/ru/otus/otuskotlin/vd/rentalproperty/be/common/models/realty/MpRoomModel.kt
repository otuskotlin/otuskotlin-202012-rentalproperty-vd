package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IMpIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.ConvenienceEnum
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.RealtyTypeEnum

class MpRoomModel(
   val id: MpRoomIdModel = MpRoomIdModel.NONE,
   val realtyType: RealtyTypeEnum = RealtyTypeEnum.ROOM,
   val price: Double,
   val area: Double,
   val address: String,
   val houseId: IMpIdModel,
   val number: Int = 1,
   val ceilingHeight: Double? = null,
   val conveniences: MutableSet<ConvenienceEnum> = mutableSetOf(),
)