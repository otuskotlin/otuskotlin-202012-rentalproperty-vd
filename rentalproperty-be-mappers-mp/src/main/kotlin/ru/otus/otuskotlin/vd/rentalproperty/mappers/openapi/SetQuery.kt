package ru.otus.otuskotlin.marketplace.mappers.openapi

import ru.otus.otuskotlin.marketplace.transport.kmp.models.common.IMpRequest
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpHouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpHouseModel
import ru.otus.otuskotlin.vd.rentalproperty.mappers.backend.toModel
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.MpRequestHouseCreate
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.MpRequestHouseRead

private fun ru.otus.otuskotlin.vd.rentalproperty.be.common.context.MpBeContext.setQuery(request: MpRequestHouseRead) {
  this.requestHouseId = request.houseId?.let {
    MpHouseIdModel(it)
  } ?: MpHouseIdModel.NONE
}

fun ru.otus.otuskotlin.vd.rentalproperty.be.common.context.MpBeContext.setQuery(request: IMpRequest) =
  when (request) {
    is MpRequestHouseRead -> setQuery(request)
    is MpRequestHouseCreate -> setQuery(request)
    else -> null
  }

private fun ru.otus.otuskotlin.vd.rentalproperty.be.common.context.MpBeContext.setQuery(request: MpRequestHouseCreate) {
  request.createData?.let { data ->
    this.requestHouse = MpHouseModel(
      realtyType = ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.RealtyTypeEnum.valueOf(data.realtyType.name),
      price = data.price ?: 0.0,
      area = data.area ?: 0.0,
      address = data.address ?: "",
      material = data.material?.let {
        ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.HouseMaterialEnum.valueOf(
          it.name
        )
      } ?: ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.HouseMaterialEnum.NONE,
      type = data.type?.let { ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.HouseTypeEnum.valueOf(it.name) }
        ?: ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.HouseTypeEnum.NONE,
      series = data.series ?: "",
      floors = data.floors ?: 0,
      areaPlot = data.areaPlot ?: 0.0,
      plotStatus = data.plotStatus?.let {
        ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.PlotStatusEnum.valueOf(
          it.name
        )
      },
      infrastructure = data.infrastructure?.map {
        ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.InfrastructureEnum.valueOf(
          it.name
        )
      }
        ?.toMutableSet() ?: mutableSetOf(),
      yearConstruction = data.yearConstruction ?: 0,
      garbageChute = data.garbageChute ?: false,
      unitOnFloor = data.unitOnFloor ?: 0,
      passengerElevator = data.passengerElevator ?: 0,
      serviceElevator = data.serviceElevator ?: 0,
      metro = data.metro ?: "",
      timeToMetro = data.timeToMetro ?: 0,
      distanceToMetro = data.distanceToMetro ?: 0,
      photos = data.photos?.map { it.toModel() }?.toMutableSet() ?: mutableSetOf(),
    )
  }
}
