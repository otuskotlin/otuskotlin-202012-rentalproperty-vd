package ru.otus.otuskotlin.vd.rentalproperty.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpHouseFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpHouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpHouseModel
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.*

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
    ?.filter { it != ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MpMediaFileModel.NONE }
    ?.map { it.toTransport() }?.toSet()
)

internal fun MpHouseDto.toModel() = MpHouseModel(
  id = id?.let { MpHouseIdModel(it) }
    ?: MpHouseIdModel.NONE,
  realtyType = ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.RealtyTypeEnum.valueOf(realtyType.name),
  price = price ?: 0.0,
  area = area ?: 0.0,
  address = address ?: "",
  material = material?.let { ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseMaterialEnum.valueOf(it.name) }
    ?: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseMaterialEnum.NONE,
  type = type?.let { ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseTypeEnum.valueOf(it.name) }
    ?: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseTypeEnum.NONE,
  series = series ?: "",
  floors = floors ?: 0,
  areaPlot = areaPlot ?: 0.0,
  plotStatus = plotStatus?.let { ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.PlotStatusEnum.valueOf(it.name) },
  infrastructure = infrastructure?.map {
    ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.InfrastructureEnum.valueOf(
      it.name
    )
  }
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

fun ru.otus.otuskotlin.vd.rentalproperty.be.common.context.MpBeContext.setQuery(query: MpRequestHouseCreate) = apply {
  requestHouse = query.createData?.toModel() ?: MpHouseModel.NONE
}

fun ru.otus.otuskotlin.vd.rentalproperty.be.common.context.MpBeContext.setQuery(query: MpRequestHouseRead) = apply {
  requestHouseId = query.houseId?.let { MpHouseIdModel(it) } ?: MpHouseIdModel.NONE
}

fun ru.otus.otuskotlin.vd.rentalproperty.be.common.context.MpBeContext.setQuery(query: MpRequestHouseUpdate) = apply {
  requestHouse = query.updateData?.toModel() ?: MpHouseModel.NONE
}

fun ru.otus.otuskotlin.vd.rentalproperty.be.common.context.MpBeContext.setQuery(query: MpRequestHouseDelete) = apply {
  requestHouseId = query.houseId?.let { MpHouseIdModel(it) } ?: MpHouseIdModel.NONE
}

fun ru.otus.otuskotlin.vd.rentalproperty.be.common.context.MpBeContext.setQuery(query: MpRequestHouseList) = apply {
  houseFilter = query.filterData?.let {
    MpHouseFilterModel(
      text = it.text ?: ""
    )
  } ?: MpHouseFilterModel.NONE
}

fun ru.otus.otuskotlin.vd.rentalproperty.be.common.context.MpBeContext.respondHouseGet() = MpResponseHouseRead(
  house = responseHouse.takeIf { it != MpHouseModel.NONE }?.toTransport()
)

fun ru.otus.otuskotlin.vd.rentalproperty.be.common.context.MpBeContext.respondHouseCreate() = MpResponseHouseCreate(
  house = responseHouse.takeIf { it != MpHouseModel.NONE }?.toTransport()
)

fun ru.otus.otuskotlin.vd.rentalproperty.be.common.context.MpBeContext.respondHouseUpdate() = MpResponseHouseUpdate(
  house = responseHouse.takeIf { it != MpHouseModel.NONE }?.toTransport()
)

fun ru.otus.otuskotlin.vd.rentalproperty.be.common.context.MpBeContext.respondHouseDelete() = MpResponseHouseDelete(
  house = responseHouse.takeIf { it != MpHouseModel.NONE }?.toTransport()
)

fun ru.otus.otuskotlin.vd.rentalproperty.be.common.context.MpBeContext.respondHouseList() = MpResponseHouseList(
  houses = responseHouses.takeIf { it.isNotEmpty() }?.filter { it != MpHouseModel.NONE }
    ?.map { it.toTransport() }
)


private fun MpHouseCreateDto.toModel() = MpHouseModel(
  realtyType = ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.RealtyTypeEnum.valueOf(realtyType.name),
  price = price ?: 0.0,
  area = area ?: 0.0,
  address = address ?: "",
  material = material?.let { ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseMaterialEnum.valueOf(it.name) }
    ?: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseMaterialEnum.NONE,
  type = type?.let { ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseTypeEnum.valueOf(it.name) }
    ?: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseTypeEnum.NONE,
  series = series ?: "",
  floors = floors ?: 0,
  areaPlot = areaPlot ?: 0.0,
  plotStatus = plotStatus?.let { ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.PlotStatusEnum.valueOf(it.name) },
  infrastructure = infrastructure?.map {
    ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.InfrastructureEnum.valueOf(
      it.name
    )
  }
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

private fun MpHouseUpdateDto.toModel() = MpHouseModel(
  id = id?.let { MpHouseIdModel(it) }
    ?: MpHouseIdModel.NONE,
  realtyType = ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.RealtyTypeEnum.valueOf(realtyType.name),
  price = price ?: 0.0,
  area = area ?: 0.0,
  address = address ?: "",
  material = material?.let { ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseMaterialEnum.valueOf(it.name) }
    ?: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseMaterialEnum.NONE,
  type = type?.let { ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseTypeEnum.valueOf(it.name) }
    ?: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseTypeEnum.NONE,
  series = series ?: "",
  floors = floors ?: 0,
  areaPlot = areaPlot ?: 0.0,
  plotStatus = plotStatus?.let { ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.PlotStatusEnum.valueOf(it.name) },
  infrastructure = infrastructure?.map {
    ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.InfrastructureEnum.valueOf(
      it.name
    )
  }
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