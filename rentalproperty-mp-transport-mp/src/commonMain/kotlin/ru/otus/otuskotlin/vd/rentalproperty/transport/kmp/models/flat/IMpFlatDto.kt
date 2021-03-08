package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.flat

import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.media.MediaFileDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.BathroomTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.ConvenienceDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.RepairTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.ViewFromWindowDto

interface IMpFlatDto {
  val houseId: String?
  val rooms: Int?
  val floor: Int?
  val areaLiving: Double?
  val areaKitchen: Double?
  val ceilingHeight: Double?  //высота потолков
  val conveniences: MutableSet<ConvenienceDto>?
  val numberResidents: Int?
  val photos: MutableSet<MediaFileDto>?
  val bedrooms: Int?
  val beds: Int?
  val bathroom: Int?
  val bathroomType: BathroomTypeDto?
  val balcony: Int?
  val loggia: Int?
  val repairType: RepairTypeDto?
  val redevelopment: Boolean?   //Перепланировка
  val noSmoking: Boolean?
  val noAnimals: Boolean?
  val noChildren: Boolean?
  val viewFromWindow: ViewFromWindowDto?
  val description: String?
}