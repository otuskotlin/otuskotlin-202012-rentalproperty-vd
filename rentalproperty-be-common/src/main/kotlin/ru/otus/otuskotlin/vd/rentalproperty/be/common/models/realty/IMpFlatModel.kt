package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

interface IMpFlatModel {
  val houseId: ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IMpIdModel
  val rooms: Int
  val floor: Int
  val areaLiving: Double
  val areaKitchen: Double
  val ceilingHeight: Double  //высота потолков
  val conveniences: MutableSet<ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.ConvenienceEnum>
  val numberResidents: Int
  val photos: MutableSet<ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFile>
  val bedrooms: Int
  val beds: Int
  val bathroom: Int
  val bathroomType: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.BathroomTypeEnum?
  val balcony: Int
  val loggia: Int
  val repairType: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.RepairTypeEnum?
  val redevelopment: Boolean   //Перепланировка
  val noSmoking: Boolean
  val noAnimals: Boolean
  val noChildren: Boolean
  val viewFromWindow: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.ViewFromWindowEnum?
  val description: String
}