package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.realty

import ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model.Id
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.HouseMaterialEnum
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.HouseTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.InfrastructureEnum
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.RealtyTypeEnum

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