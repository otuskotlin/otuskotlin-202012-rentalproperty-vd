package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house

import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.MpItemPermission
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.media.MediaFileDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.*

data class MpHouseDto(
  override val id: String? = null,
  override val permissions: Set<MpItemPermission>? = null,
  override val realtyType: RealtyTypeDto = RealtyTypeDto.HOUSE,
  override val price: Double? = null,
  override val area: Double? = null,
  override val address: String? = null,
  override val material: HouseMaterialDto? = null,
  override val type: HouseTypeDto? = null,
  override val series: String? = null,
  override val floors: Int? = null,
  override val areaPlot: Double? = null,
  override val plotStatus: PlotStatusDto? = null,
  override val infrastructure: Set<InfrastructureDto>? = null,
  override val yearConstruction: Int? = null,
  override val garbageChute: Boolean? = null,
  override val unitOnFloor: Int? = null,
  override val passengerElevator: Int? = null,
  override val serviceElevator: Int? = null,
  override val metro: String? = null,
  override val timeToMetro: Int? = null,
  override val distanceToMetro: Int? = null,
  override val photos: Set<MediaFileDto>? = null
) : IMpRealtyDto, IMpHouseDto