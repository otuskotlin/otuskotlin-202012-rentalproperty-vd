package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.BathroomTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.ConvenienceEnum
import ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.RepairTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.ViewFromWindowEnum
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IMpIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MpMediaFileModel

interface IMpFlatModel {
  val houseId: IMpIdModel
  val rooms: Int
  val floor: Int
  val areaLiving: Double
  val areaKitchen: Double
  val ceilingHeight: Double  //высота потолков
  val conveniences: MutableSet<ConvenienceEnum>
  val numberResidents: Int
  val photos: MutableSet<MpMediaFileModel>
  val bedrooms: Int
  val beds: Int
  val bathroom: Int
  val bathroomType: BathroomTypeEnum?
  val balcony: Int
  val loggia: Int
  val repairType: RepairTypeEnum?
  val redevelopment: Boolean   //Перепланировка
  val noSmoking: Boolean
  val noAnimals: Boolean
  val noChildren: Boolean
  val viewFromWindow: ViewFromWindowEnum?
  val description: String
}