package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat

import kotlinx.serialization.Serializable
import media.MediaFileDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ItemPermission
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.*

@Serializable
data class FlatDto(
  val id: String? = null,
  val permissions: Set<ItemPermission>? = null,
  val houseId: String? = null,
  val number: String? = null,
  val area: Double? = null,
  val areaLiving: Double? = null,
  val areaKitchen: Double? = null,
  val rooms: Int? = null,
  val floor: Int? = null,
  val ceilingHeight: Double? = null,
  val bedrooms: Int? = null,
  val beds: Int? = null,
  val bathroom: Int? = null,
  val bathroomType: BathroomTypeDto? = null,
  val balcony: Int? = null,
  val loggia: Int? = null,
  val repairType: RepairTypeDto? = null,
  val viewFromWindow: ViewFromWindowDto? = null,
  val conveniences: Set<ConveniencesDto>? = null,
  val appliances: Set<AppliancesDto>? = null,
  val residents: Int? = null,
  val noSmoking: Boolean? = null,
  val noAnimals: Boolean? = null,
  val noChildren: Boolean? = null,
  val noParties: Boolean? = null,
  val description: String? = null,
  val photos: Set<MediaFileDto>? = null,
)