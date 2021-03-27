package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat

import kotlinx.serialization.Serializable
import media.MediaFileDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.BathroomTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.ConvenienceTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.RepairTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.ViewFromWindowDto

@Serializable
data class FlatUpdateDto(
  val id: String? = null,
  val price: Double? = null,
  val area: Double? = null,
  val address: String? = null,
  val houseId: String? = null,
  val rooms: Int? = null,
  val floor: Int? = null,
  val areaLiving: Double? = null,
  val areaKitchen: Double? = null,
  val ceilingHeight: Double? = null,
  val conveniences: MutableSet<ConvenienceTypeDto>? = null,
  val numberResidents: Int? = null,
  val photos: MutableSet<MediaFileDto>? = null,
  val bedrooms: Int? = null,
  val beds: Int? = null,
  val bathroom: Int? = null,
  val bathroomType: BathroomTypeDto? = null,
  val balcony: Int? = null,
  val loggia: Int? = null,
  val repairType: RepairTypeDto? = null,
  val redevelopment: Boolean? = null,
  val noSmoking: Boolean? = null,
  val noAnimals: Boolean? = null,
  val noChildren: Boolean? = null,
  val viewFromWindow: ViewFromWindowDto? = null,
  val description: String? = null,
)