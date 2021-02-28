package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.room

import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.MpItemPermission
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.ConvenienceDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.IMpRealtyDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.RealtyTypeDto

class MpRoomDto(
  override val id: String? = null,
  override val permissions: Set<MpItemPermission>? = null,
  override val realtyType: RealtyTypeDto = RealtyTypeDto.ROOM,
  override val price: Double? = null,
  override val area: Double? = null,
  override val address: String? = null,
  override val houseId: String? = null,
  override val number: Int? = null,
  override val ceilingHeight: Double? = null,
  override val conveniences: MutableSet<ConvenienceDto>? = null,
) : IMpRealtyDto, IMpRoomDto