package ru.otus.otuskotlin.vd.rentalproperty.be.mappers

import ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.*
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MpMediaFileModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpHouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpHouseModel
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.MpHouseDto

internal fun MpHouseModel.toTransport() = MpHouseDto(
  id = id.id.takeIf { it.isNotBlank() },
  realtyType = RealtyTypeDto.valueOf(realtyType.name),
  price = price.takeIf { it != 0.0 },
  area = area.takeIf { it != 0.0 },
  address = address.takeIf { it.isNotBlank() },
  material = HouseMaterialDto.valueOf(material.name),
  type = HouseTypeDto.valueOf(type.name),
  series = series.takeIf { it.isNotBlank() },
  floors = floors.takeIf { it != 0 },
  areaPlot = areaPlot.takeIf { it != 0.0 },
  plotStatus = plotStatus?.let { PlotStatusDto.valueOf(it.name) },
  infrastructure = infrastructure.takeIf { it.isNotEmpty() }
    ?.map { InfrastructureDto.valueOf(it.name) }?.toSet(),
  yearConstruction = yearConstruction.takeIf { it != 0 },
  garbageChute = garbageChute.takeIf { it },
  unitOnFloor = unitOnFloor.takeIf { it != 0 },
  passengerElevator = passengerElevator.takeIf { it != 0 },
  serviceElevator = serviceElevator.takeIf { it != 0 },
  metro = metro.takeIf { it.isNotBlank() },
  timeToMetro = timeToMetro.takeIf { it != 0 },
  distanceToMetro = distanceToMetro.takeIf { it != 0 },
  photos = photos.takeIf { it.isNotEmpty() }
    ?.filter { it != MpMediaFileModel.NONE }?.map { it.toTransport() }?.toSet()
)

internal fun MpHouseDto.toModel() = MpHouseModel(
  id = id?.let { MpHouseIdModel(it) } ?: MpHouseIdModel.NONE,
  realtyType = RealtyTypeEnum.valueOf(realtyType.name),
  price = price ?: 0.0,
  area = area ?: 0.0,
  address = address ?: "",
  material = material?.let { HouseMaterialEnum.valueOf(it.name) } ?: HouseMaterialEnum.NONE,
  type = type?.let { HouseTypeEnum.valueOf(it.name) } ?: HouseTypeEnum.NONE,
  series = series ?: "",
  floors = floors ?: 0,
  areaPlot = areaPlot ?: 0.0,
  plotStatus = plotStatus?.let { PlotStatusEnum.valueOf(it.name) },
  infrastructure = infrastructure?.map { InfrastructureEnum.valueOf(it.name) }
    ?.toMutableSet() ?: mutableSetOf(),
  yearConstruction = yearConstruction ?: 0,
  garbageChute = garbageChute ?: false,
  unitOnFloor = unitOnFloor ?: 0,
  passengerElevator = passengerElevator ?: 0,
  serviceElevator = serviceElevator ?: 0,
  metro = metro ?: "",
  timeToMetro = timeToMetro ?: 0,
  distanceToMetro = distanceToMetro ?: 0,
  photos = photos?.map { it.toModel() }?.toMutableSet() ?: mutableSetOf(),
)