package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat

import kotlinx.serialization.Serializable
import media.MediaFileDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.*

@Serializable
data class FlatUpdateDto(
  val id: String? = null,
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
  val conveniences: MutableSet<ConveniencesDto>? = null,
  val appliances: MutableSet<AppliancesDto>? = null,
  val residents: Int? = null,
  val noSmoking: Boolean? = null,
  val noAnimals: Boolean? = null,
  val noChildren: Boolean? = null,
  val noParties: Boolean? = null,
  val description: String? = null,
  val photos: MutableSet<MediaFileDto>? = null,
) {
  companion object {
    val STUB = FlatUpdateDto(
      id = "test-flat-id",
      houseId = "test-house-id",
      area = 44.4,
      areaLiving = 28.0,
      areaKitchen = 4.0,
      rooms = 2,
      floor = 3,
      ceilingHeight = 2.5,
      bedrooms = 1,
      beds = 1,
      bathroom = 1,
      bathroomType = BathroomTypeDto.STUB_COMBINED,
      balcony = 0,
      loggia = 0,
      repairType = RepairTypeDto.STUB_RENOVATION,
      viewFromWindow = ViewFromWindowDto.STUB_PARK,
      conveniences = mutableSetOf(
        ConveniencesDto.STUB_GAS,
        ConveniencesDto.STUB_FURNITURE_IN_KITCHEN,
        ConveniencesDto.STUB_PARKING,
      ),
      appliances = mutableSetOf(
        AppliancesDto.STUB_AIR_CONDITIONER,
        AppliancesDto.STUB_KITCHEN_STOVE,
        AppliancesDto.STUB_INTERNET,
      ),
      residents = 4,
      noSmoking = true,
      noAnimals = false,
      noChildren = false,
      noParties = true,
      description = "Хрущёвка",
      photos = mutableSetOf(),
    )
    val STUB2 = FlatUpdateDto(
      id = "test-flat-id-2",
      houseId = "test-house-id",
      number = "22",
      area = 52.0,
      rooms = 3,
      floor = 2,
      balcony = 1,
      description = "Улучшенка",
    )
    val STUB3 = FlatUpdateDto(
      id = "test-flat-id-3",
      houseId = "test-house-id",
      number = "33",
      area = 73.0,
      rooms = 4,
      floor = 7,
      balcony = 2,
      description = "Распашонка",
    )
  }
}