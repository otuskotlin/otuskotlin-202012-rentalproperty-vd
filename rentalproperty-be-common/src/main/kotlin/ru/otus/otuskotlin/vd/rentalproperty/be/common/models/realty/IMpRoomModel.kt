package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

interface IMpRoomModel {
  val houseId: ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IMpIdModel
  val number: Int
  val ceilingHeight: Double?  //высота потолков
  val conveniences: MutableSet<ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.ConvenienceEnum>
}