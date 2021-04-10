package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house

import kotlinx.serialization.Serializable
import media.MediaFileDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.HouseMaterialDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.HouseTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.InfrastructureDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.PlotStatusDto

@Serializable
data class HouseCreateDto(
  val address: String? = null,
  val area: Double? = null,
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
) {
  companion object {
    val STUB_SINGLE_HOUSE = HouseCreateDto(
      address = "test-address",
      area = 160.4,
      material = HouseMaterialDto.STUB_BRICK,
      type = HouseTypeDto.STUB_SINGLE_HOUSE,
      series = "",
      floors = 2,
      areaPlot = 15.0,
      plotStatus = PlotStatusDto.STUB_IRP,
      infrastructure = mutableSetOf(
        InfrastructureDto.STUB_GAZ,
        InfrastructureDto.STUB_ELECTRICITY,
        InfrastructureDto.STUB_SEWERAGE,
        InfrastructureDto.STUB_WATER,
      ),
      yearConstruction = 2005,
      garbageChute = false,
      unitOnFloor = 3,
      passengerElevator = 0,
      serviceElevator = 0,
      metro = "",
      timeToMetro = 0,
      distanceToMetro = 0,
      photos = mutableSetOf(),
    )
  }
}