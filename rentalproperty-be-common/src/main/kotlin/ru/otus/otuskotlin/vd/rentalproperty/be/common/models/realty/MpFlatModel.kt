package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

data class MpFlatModel(
  override val id: ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpFlatIdModel = ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpFlatIdModel.Companion.NONE,
  override val realtyType: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.RealtyTypeEnum = ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.RealtyTypeEnum.FLAT,
  override val price: Double,
  override val area: Double,
  override val address: String,
  override val houseId: ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IMpIdModel,
  override val rooms: Int,
  override val floor: Int = 0,
  override val areaLiving: Double = 0.0,
  override val areaKitchen: Double = 0.0,
  override val ceilingHeight: Double = 0.0,  //высота потолков
  override val conveniences: MutableSet<ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.ConvenienceEnum> = mutableSetOf(),
  override val numberResidents: Int = 0,
  override val photos: MutableSet<ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFile> = mutableSetOf(),
  override val bedrooms: Int = 0,
  override val beds: Int = 0,
  override val bathroom: Int = 0,
  override val bathroomType: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.BathroomTypeEnum? = null,
  override val balcony: Int = 0,
  override val loggia: Int = 0,
  override val repairType: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.RepairTypeEnum? = null,
  override val redevelopment: Boolean = false,   //Перепланировка
  override val noSmoking: Boolean = false,
  override val noAnimals: Boolean = false,
  override val noChildren: Boolean = false,
  override val viewFromWindow: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.ViewFromWindowEnum? = null,
  override val description: String = "",
) : ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.IMpRealtyModel,
  ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.IMpFlatModel