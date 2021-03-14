package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.media.MediaFileDto

@Serializable
data class HouseUpdateDto(
  val id: String? = null,
  val realtyType: RealtyTypeEnum = RealtyTypeEnum.HOUSE,
  val price: Double? = null,
  val area: Double? = null,
  val address: String? = null,
  val material: HouseMaterialEnum? = null,
  val type: HouseTypeEnum? = null,
  val series: String? = null,
  val floors: Int? = null,
  val areaPlot: Double? = null,
  val plotStatus: PlotStatusEnum? = null,
  val infrastructure: MutableSet<InfrastructureEnum>? = null,
  val yearConstruction: Int? = null,
  val garbageChute: Boolean? = null,
  val unitOnFloor: Int? = null,
  val passengerElevator: Int? = null,
  val serviceElevator: Int? = null,
  val metro: String? = null,
  val timeToMetro: Int? = null,
  val distanceToMetro: Int? = null,
  val photos: MutableSet<MediaFileDto>? = null
)