package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

interface IMpHouseModel {
  val material: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseMaterialEnum
  val type: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseTypeEnum
  val series: String //Серия дома
  val floors: Int
  val areaPlot: Double  //площадь участка
  val plotStatus: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.PlotStatusEnum?
  val infrastructure: MutableSet<ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.InfrastructureEnum>
  val yearConstruction: Int
  val garbageChute: Boolean //мусоропровод
  val unitOnFloor: Int   //квартир на этаже
  val passengerElevator: Int
  val serviceElevator: Int
  val metro: String
  val timeToMetro: Int
  val distanceToMetro: Int
  val photos: MutableSet<ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFile>
}