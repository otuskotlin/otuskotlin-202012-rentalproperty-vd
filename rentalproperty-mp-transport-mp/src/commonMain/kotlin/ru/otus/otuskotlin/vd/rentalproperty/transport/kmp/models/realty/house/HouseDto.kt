package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.realty.house

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ItemPermission
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.media.MediaFileDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.directory.HouseMaterialDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.directory.HouseTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.directory.InfrastructureDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.directory.PlotStatusDto

/**
 * House parameters
 */
@Serializable
data class HouseDto(
  val id: String? = null,
  val permissions: Set<ItemPermission>? = null,
  val area: Double? = null,
  val address: String? = null,
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