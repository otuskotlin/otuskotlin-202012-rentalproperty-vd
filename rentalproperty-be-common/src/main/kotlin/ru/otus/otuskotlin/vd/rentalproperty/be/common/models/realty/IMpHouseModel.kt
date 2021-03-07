package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseMaterialEnum
import ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.InfrastructureEnum
import ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.PlotStatusEnum
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MpMediaFileModel

interface IMpHouseModel {
  val material: HouseMaterialEnum
  val type: HouseTypeEnum
  val series: String //Серия дома
  val floors: Int
  val areaPlot: Double  //площадь участка
  val plotStatus: PlotStatusEnum?
  val infrastructure: MutableSet<InfrastructureEnum>
  val yearConstruction: Int
  val garbageChute: Boolean //мусоропровод
  val unitOnFloor: Int   //квартир на этаже
  val passengerElevator: Int
  val serviceElevator: Int
  val metro: String
  val timeToMetro: Int
  val distanceToMetro: Int
  val photos: MutableSet<MpMediaFileModel>
}