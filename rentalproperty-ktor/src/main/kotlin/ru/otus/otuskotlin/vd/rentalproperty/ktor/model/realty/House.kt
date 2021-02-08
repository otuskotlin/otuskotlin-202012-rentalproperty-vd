package ru.otus.otuskotlin.vd.rentalproperty.ktor.model.realty

import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.HouseMaterialEnum
import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.HouseTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.InfrastructureEnum
import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.RealtyTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.ktor.model.Id

data class House(
  override var id: Id = Id.NONE,
  override var realtyType: RealtyTypeEnum = RealtyTypeEnum.HOUSE,
  override var price: Double,
  override var area: Double,
  override var address: String,
  var material: HouseMaterialEnum,
  var type: HouseTypeEnum,
  var floor: Int = 0,
  var totalFloor: Int = 0,
  var plottage: Double = 0.0,  //площадь участка
  var infrastructure: MutableSet<InfrastructureEnum> = mutableSetOf(),
  var yearConstruction: Int = 0
) : Realty()