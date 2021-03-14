package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.flat

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ItemPermission
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.media.MediaFileDto

@Serializable
data class FlatDto(
  val id: String? = null,
  val permissions: Set<ItemPermission>? = null,
  val realtyType: RealtyTypeEnum = RealtyTypeEnum.FLAT,
  val price: Double? = null,
  val area: Double? = null,
  val address: String? = null,
  val houseId: String? = null,
  val rooms: Int? = null,
  val floor: Int? = null,
  val areaLiving: Double? = null,
  val areaKitchen: Double? = null,
  val ceilingHeight: Double? = null,
  val conveniences: MutableSet<ConvenienceEnum>? = null,
  val numberResidents: Int? = null,
  val photos: MutableSet<MediaFileDto>? = null,
  val bedrooms: Int? = null,
  val beds: Int? = null,
  val bathroom: Int? = null,
  val bathroomType: BathroomTypeEnum? = null,
  val balcony: Int? = null,
  val loggia: Int? = null,
  val repairType: RepairTypeEnum? = null,
  val redevelopment: Boolean? = null,
  val noSmoking: Boolean? = null,
  val noAnimals: Boolean? = null,
  val noChildren: Boolean? = null,
  val viewFromWindow: ViewFromWindowEnum? = null,
  val description: String? = null,
)