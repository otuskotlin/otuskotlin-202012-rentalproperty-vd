package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.realty

import ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model.Id
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.*

data class House(
  override var id: Id = Id.NONE,
  override var realtyType: RealtyTypeEnum = RealtyTypeEnum.HOUSE,
  override var price: Double,
  override var area: Double,
  override var address: String,
  var material: HouseMaterialEnum,
  var type: HouseTypeEnum,
  var series: String = "",  //Серия дома
  var floors: Int = 0,
  var areaPlot: Double = 0.0,  //площадь участка
  var plotStatus: PlotStatusEnum? = null,
  var infrastructure: MutableSet<InfrastructureEnum> = mutableSetOf(),
  var yearConstruction: Int = 0,
  var garbageChute: Boolean = false, //мусоропровод
  var unitOnFloor: Int = 0,   //квартир на этаже
  var passengerElevator: Int = 0,
  var serviceElevator: Int = 0,
  var metro: String? = "",
  var timeToMetro: Int? = 0,
  var distanceToMetro: Int? = 0,
  var photos: MutableSet<InfrastructureEnum>? = null
) : Realty()