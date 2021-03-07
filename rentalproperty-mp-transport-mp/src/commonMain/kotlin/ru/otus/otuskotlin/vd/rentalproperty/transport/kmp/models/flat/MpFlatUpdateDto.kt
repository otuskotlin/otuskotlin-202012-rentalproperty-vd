package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.flat

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.media.MediaFileDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.*

@Serializable
data class MpFlatUpdateDto(
  override val id: String? = null,
  override val realtyType: RealtyTypeDto = RealtyTypeDto.FLAT,
  override val price: Double? = null,
  override val area: Double? = null,
  override val address: String? = null,
  override val houseId: String? = null,
  override val rooms: Int? = null,
  override val floor: Int? = null,
  override val areaLiving: Double? = null,
  override val areaKitchen: Double? = null,
  override val ceilingHeight: Double? = null,
  override val conveniences: MutableSet<ConvenienceDto>? = null,
  override val numberResidents: Int? = null,
  override val photos: MutableSet<MediaFileDto>? = null,
  override val bedrooms: Int? = null,
  override val beds: Int? = null,
  override val bathroom: Int? = null,
  override val bathroomType: BathroomTypeDto? = null,
  override val balcony: Int? = null,
  override val loggia: Int? = null,
  override val repairType: RepairTypeDto? = null,
  override val redevelopment: Boolean? = null,
  override val noSmoking: Boolean? = null,
  override val noAnimals: Boolean? = null,
  override val noChildren: Boolean? = null,
  override val viewFromWindow: ViewFromWindowDto? = null,
  override val description: String? = null,
) : IMpRealtyUpdateDto, IMpFlatDto