package ru.otus.otuskotlin.vd.rentalproperty.ktor.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.ConvenienceEnum
import ru.otus.otuskotlin.vd.rentalproperty.ktor.models.IMpIdModel

interface IMpRoomModel {
  val houseId: IMpIdModel
  val number: Int
  val ceilingHeight: Double?  //высота потолков
  val conveniences: MutableSet<ConvenienceEnum>
}