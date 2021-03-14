package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.room

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.ConvenienceEnum
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.RealtyTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.MpItemPermission

@Serializable
class MpRoomDto(
  val id: String? = null,
  val permissions: Set<MpItemPermission>? = null,
  val realtyType: RealtyTypeEnum = RealtyTypeEnum.ROOM,
  val price: Double? = null,
  val area: Double? = null,
  val address: String? = null,
  val houseId: String? = null,
  val number: Int? = null,
  val ceilingHeight: Double? = null,
  val conveniences: MutableSet<ConvenienceEnum>? = null,
)