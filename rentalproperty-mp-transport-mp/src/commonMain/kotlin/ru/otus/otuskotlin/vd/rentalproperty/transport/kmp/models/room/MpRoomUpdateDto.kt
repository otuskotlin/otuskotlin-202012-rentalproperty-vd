package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.room

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.ConvenienceDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.IMpRealtyUpdateDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.RealtyTypeDto

@Serializable
class MpRoomUpdateDto(
  override val id: String? = null,
  override val realtyType: RealtyTypeDto = RealtyTypeDto.ROOM,
  override val price: Double? = null,
  override val area: Double? = null,
  override val address: String? = null,
  override val houseId: String? = null,
  override val number: Int? = null,
  override val ceilingHeight: Double? = null,
  override val conveniences: MutableSet<ConvenienceDto>? = null,
) : IMpRealtyUpdateDto, IMpRoomDto