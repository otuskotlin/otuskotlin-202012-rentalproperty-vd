package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.room

import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.ConvenienceDto

interface IMpRoomDto {
  val houseId: String?
  val number: Int?
  val ceilingHeight: Double?  //высота потолков
  val conveniences: MutableSet<ConvenienceDto>?
}