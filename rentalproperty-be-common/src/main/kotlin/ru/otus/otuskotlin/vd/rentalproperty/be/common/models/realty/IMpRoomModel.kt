package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.ConvenienceEnum
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IMpIdModel

interface IMpRoomModel {
  val houseId: IMpIdModel
  val number: Int
  val ceilingHeight: Double?  //высота потолков
  val conveniences: MutableSet<ConvenienceEnum>
}