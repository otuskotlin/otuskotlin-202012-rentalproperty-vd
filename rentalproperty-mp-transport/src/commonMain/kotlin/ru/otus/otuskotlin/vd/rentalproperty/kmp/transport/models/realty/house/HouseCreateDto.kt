package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house

import kotlinx.serialization.Serializable
import media.MediaFileDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.HouseMaterialDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.HouseTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.InfrastructureDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.PlotStatusDto

@Serializable
data class HouseCreateDto(
  val area: Double? = null,
  val address: String? = null,
  val material: HouseMaterialDto? = null,
  val type: HouseTypeDto? = null,
  val series: String? = null,
  val floors: Int? = null,
  val areaPlot: Double? = null,
  val plotStatus: PlotStatusDto? = null,
  val infrastructure: MutableSet<InfrastructureDto>? = null,
  val yearConstruction: Int? = null,
  val garbageChute: Boolean? = null,
  val unitOnFloor: Int? = null,
  val passengerElevator: Int? = null,
  val serviceElevator: Int? = null,
  val metro: String? = null,
  val timeToMetro: Int? = null,
  val distanceToMetro: Int? = null,
  val photos: MutableSet<MediaFileDto>? = null,
)