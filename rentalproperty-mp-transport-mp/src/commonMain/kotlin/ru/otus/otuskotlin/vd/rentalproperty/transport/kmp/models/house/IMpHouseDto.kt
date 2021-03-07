package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house

import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.media.MediaFileDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.HouseMaterialDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.HouseTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.InfrastructureDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.PlotStatusDto

interface IMpHouseDto {
  val material: HouseMaterialDto?
  val type: HouseTypeDto?
  val series: String? //Серия дома
  val floors: Int?
  val areaPlot: Double?  //площадь участка
  val plotStatus: PlotStatusDto?
  val infrastructure: MutableSet<InfrastructureDto>?
  val yearConstruction: Int?
  val garbageChute: Boolean? //мусоропровод
  val unitOnFloor: Int?   //квартир на этаже
  val passengerElevator: Int?
  val serviceElevator: Int?
  val metro: String?
  val timeToMetro: Int?
  val distanceToMetro: Int?
  val photos: MutableSet<MediaFileDto>?
}