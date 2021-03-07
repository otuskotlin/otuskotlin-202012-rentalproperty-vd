package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

class MpRoomModel(
  override val id: ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpRoomIdModel = ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpRoomIdModel.Companion.NONE,
  override val realtyType: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.RealtyTypeEnum = ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.RealtyTypeEnum.ROOM,
  override val price: Double,
  override val area: Double,
  override val address: String,
  override val houseId: ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IMpIdModel,
  override val number: Int = 1,
  override val ceilingHeight: Double? = null,
  override val conveniences: MutableSet<ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.ConvenienceEnum> = mutableSetOf(),
) : ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.IMpRealtyModel,
  ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.IMpRoomModel