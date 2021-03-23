package ru.otus.otuskotlin.vd.rentalproperty.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseMaterialModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.PlotStatusModel
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.realty.house.*

internal fun HouseModel.toTransport() = HouseDto(
  id = id.id.takeIf { it.isNotBlank() },
  area = area.takeIf { it != 0.0 },
  address = address.takeIf { it.isNotBlank() },
  material = material.toTransport(),
  type = type.toTransport(),
  series = series.takeIf { it.isNotBlank() },
  floors = floors.takeIf { it != 0 },
  areaPlot = areaPlot.takeIf { it != 0.0 },
  plotStatus = plotStatus?.toTransport(),
  infrastructure = infrastructure.takeIf { it.isNotEmpty() }
    ?.map { it.toTransport() }?.toSet(),
  yearConstruction = yearConstruction.takeIf { it != 0 },
  garbageChute = garbageChute.takeIf { it },
  unitOnFloor = unitOnFloor.takeIf { it != 0 },
  passengerElevator = passengerElevator.takeIf { it != 0 },
  serviceElevator = serviceElevator.takeIf { it != 0 },
  metro = metro.takeIf { it.isNotBlank() },
  timeToMetro = timeToMetro.takeIf { it != 0 },
  distanceToMetro = distanceToMetro.takeIf { it != 0 },
  photos = photos.takeIf { it.isNotEmpty() }
    ?.filter { it != MediaFileModel.NONE }
    ?.map { it.toTransport() }?.toSet()
)

internal fun HouseDto.toModel() = HouseModel(
  id = id?.let { HouseIdModel(it) }
    ?: HouseIdModel.NONE,
  area = area ?: 0.0,
  address = address ?: "",
  material = material?.toModel() ?: HouseMaterialModel.NONE,
  type = type?.toModel() ?: HouseTypeModel.NONE,
  series = series ?: "",
  floors = floors ?: 0,
  areaPlot = areaPlot ?: 0.0,
  plotStatus = plotStatus?.toModel() ?: PlotStatusModel.NONE,
  infrastructure = infrastructure?.map { it.toModel() }
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

fun BeContext.setQuery(query: RequestHouseCreate) = apply {
  requestHouse = query.createData?.toModel() ?: HouseModel.NONE
}

fun BeContext.setQuery(query: RequestHouseRead) = apply {
  requestHouseId = query.houseId?.let { HouseIdModel(it) } ?: HouseIdModel.NONE
}

fun BeContext.setQuery(query: RequestHouseUpdate) = apply {
  requestHouse = query.updateData?.toModel() ?: HouseModel.NONE
}

fun BeContext.setQuery(query: RequestHouseDelete) = apply {
  requestHouseId = query.houseId?.let { HouseIdModel(it) } ?: HouseIdModel.NONE
}

fun BeContext.setQuery(query: RequestHouseList) = apply {
  houseFilter = query.filterData?.let {
    HouseFilterModel(
      text = it.text ?: ""
    )
  } ?: HouseFilterModel.NONE
}

fun BeContext.respondHouseGet() = ResponseHouseRead(
  house = responseHouse.takeIf { it != HouseModel.NONE }?.toTransport()
)

fun BeContext.respondHouseCreate() = ResponseHouseCreate(
  house = responseHouse.takeIf { it != HouseModel.NONE }?.toTransport()
)

fun BeContext.respondHouseUpdate() = ResponseHouseUpdate(
  house = responseHouse.takeIf { it != HouseModel.NONE }?.toTransport()
)

fun BeContext.respondHouseDelete() = ResponseHouseDelete(
  house = responseHouse.takeIf { it != HouseModel.NONE }?.toTransport()
)

fun BeContext.respondHouseList() = ResponseHouseList(
  houses = responseHouses.takeIf { it.isNotEmpty() }?.filter { it != HouseModel.NONE }
    ?.map { it.toTransport() }
)

private fun HouseCreateDto.toModel() = HouseModel(
  area = area ?: 0.0,
  address = address ?: "",
  material = material?.toModel() ?: HouseMaterialModel.NONE,
  type = type?.toModel() ?: HouseTypeModel.NONE,
  series = series ?: "",
  floors = floors ?: 0,
  areaPlot = areaPlot ?: 0.0,
  plotStatus = plotStatus?.toModel() ?: PlotStatusModel.NONE,
  infrastructure = infrastructure?.map { it.toModel() }
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

private fun HouseUpdateDto.toModel() = HouseModel(
  id = id?.let { HouseIdModel(it) }
    ?: HouseIdModel.NONE,
  area = area ?: 0.0,
  address = address ?: "",
  material = material?.toModel() ?: HouseMaterialModel.NONE,
  type = type?.toModel() ?: HouseTypeModel.NONE,
  series = series ?: "",
  floors = floors ?: 0,
  areaPlot = areaPlot ?: 0.0,
  plotStatus = plotStatus?.toModel() ?: PlotStatusModel.NONE,
  infrastructure = infrastructure?.map { it.toModel() }
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