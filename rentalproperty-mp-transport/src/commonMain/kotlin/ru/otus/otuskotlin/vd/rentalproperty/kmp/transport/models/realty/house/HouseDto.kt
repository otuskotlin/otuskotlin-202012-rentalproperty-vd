package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house

import kotlinx.serialization.Serializable
import media.MediaFileDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ItemPermission
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.HouseMaterialDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.HouseTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.InfrastructureDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.PlotStatusDto

/**
 * House parameters
 */
@Serializable
data class HouseDto(
  val id: String? = null,
  val permissions: Set<ItemPermission>? = null,
  val address: String? = null,
  val area: Double? = null,
  val material: HouseMaterialDto? = null,
  val type: HouseTypeDto? = null,
  val series: String? = null,
  val floors: Int? = null,
  val areaPlot: Double? = null,
  val plotStatus: PlotStatusDto? = null,
  val infrastructure: Set<InfrastructureDto>? = null,
  val yearConstruction: Int? = null,
  val garbageChute: Boolean? = null,
  val unitOnFloor: Int? = null,
  val passengerElevator: Int? = null,
  val serviceElevator: Int? = null,
  val metro: String? = null,
  val timeToMetro: Int? = null,
  val distanceToMetro: Int? = null,
  val photos: Set<MediaFileDto>? = null
)