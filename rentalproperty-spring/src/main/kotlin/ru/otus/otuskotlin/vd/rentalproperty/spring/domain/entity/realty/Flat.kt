package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.realty

import ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model.Id
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.*

data class Flat(
  override var id: Id = Id.NONE,
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
  var photos: MutableSet<InfrastructureEnum>? = null,
  var bedrooms: Int = 0,
  var beds: Int = 0,
  var bathroom: Int = 0,
  var typeBathroom: TypeBathroomEnum? = null,
  var balcony: Int = 0,
  var loggia: Int = 0,
  var typeRepair: TypeRepairEnum? = null,
  var redevelopment: Boolean = false,   //Перепланировка
  var noSmoking: Boolean = false,
  var noAnimals: Boolean = false,
  var noChildren: Boolean = false,
  var viewFromWindow: ViewFromWindowEnum? = null
) : Realty()