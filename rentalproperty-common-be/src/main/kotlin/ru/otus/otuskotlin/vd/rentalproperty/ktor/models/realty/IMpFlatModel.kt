package ru.otus.otuskotlin.vd.rentalproperty.ktor.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.BathroomTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.ConvenienceEnum
import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.RepairTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.ViewFromWindowEnum
import ru.otus.otuskotlin.vd.rentalproperty.ktor.models.IMpIdModel
import ru.otus.otuskotlin.vd.rentalproperty.ktor.models.media.MediaFile

interface IMpFlatModel {
  val houseId: IMpIdModel
  val rooms: Int
  val floor: Int
  val areaLiving: Double
  val areaKitchen: Double
  val ceilingHeight: Double  //высота потолков
  val conveniences: MutableSet<ConvenienceEnum>
  val numberResidents: Int
  val photos: MutableSet<MediaFile>
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