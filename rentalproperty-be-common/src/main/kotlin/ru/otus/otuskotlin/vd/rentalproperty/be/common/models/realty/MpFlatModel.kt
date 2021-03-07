package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.*
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IMpIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MpMediaFileModel

data class MpFlatModel(
  override val id: MpFlatIdModel = MpFlatIdModel.NONE,
  override val realtyType: RealtyTypeEnum = RealtyTypeEnum.FLAT,
  override val price: Double,
  override val area: Double,
  override val address: String,
  override val houseId: IMpIdModel,
  override val rooms: Int,
  override val floor: Int = 0,
  override val areaLiving: Double = 0.0,
  override val areaKitchen: Double = 0.0,
  override val ceilingHeight: Double = 0.0,  //высота потолков
  override val conveniences: MutableSet<ConvenienceEnum> = mutableSetOf(),
  override val numberResidents: Int = 0,
  override val photos: MutableSet<MpMediaFileModel> = mutableSetOf(),
  override val bedrooms: Int = 0,
  override val beds: Int = 0,
  override val bathroom: Int = 0,
  override val bathroomType: BathroomTypeEnum? = null,
  override val balcony: Int = 0,
  override val loggia: Int = 0,
  override val repairType: RepairTypeEnum? = null,
  override val redevelopment: Boolean = false,   //Перепланировка
  override val noSmoking: Boolean = false,
  override val noAnimals: Boolean = false,
  override val noChildren: Boolean = false,
  override val viewFromWindow: ViewFromWindowEnum? = null,
  override val description: String = "",
) : IMpRealtyModel, IMpFlatModel