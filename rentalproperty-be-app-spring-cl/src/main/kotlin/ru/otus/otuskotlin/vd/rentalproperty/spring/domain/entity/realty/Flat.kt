package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.realty

import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.meida.MediaFile
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.*

data class Flat(
  override var id: Long,
  override var realtyType: RealtyTypeEnum = RealtyTypeEnum.FLAT,
  override var price: Double,
  override var area: Double,
  override var address: String,
  var house: House,
  var rooms: Int,
  var floor: Int = 0,
  var areaLiving: Double = 0.0,
  var areaKitchen: Double = 0.0,
  var ceilingHeight: Double = 0.0,  //высота потолков
  var conveniences: MutableSet<ConvenienceEnum> = mutableSetOf(),
  var numberResidents: Int = 0,
  var photos: MutableSet<MediaFile>? = null,
  var bedrooms: Int = 0,
  var beds: Int = 0,
  var bathroom: Int = 0,
  var bathroomType: BathroomTypeEnum? = null,
  var balcony: Int = 0,
  var loggia: Int = 0,
  var repairType: RepairTypeEnum? = null,
  var redevelopment: Boolean = false,   //Перепланировка
  var noSmoking: Boolean = false,
  var noAnimals: Boolean = false,
  var noChildren: Boolean = false,
  var viewFromWindow: ViewFromWindowEnum? = null,
  var description: String = "",
) : Realty()