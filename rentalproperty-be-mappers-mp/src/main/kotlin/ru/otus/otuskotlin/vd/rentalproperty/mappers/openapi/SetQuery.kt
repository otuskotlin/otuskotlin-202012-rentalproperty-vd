package ru.otus.otuskotlin.marketplace.mappers.openapi

import ru.otus.otuskotlin.marketplace.transport.kmp.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.*
import ru.otus.otuskotlin.vd.rentalproperty.mappers.backend.toModel
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.RequestHouseCreate
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.RequestHouseRead

private fun BeContext.setQuery(request: RequestHouseRead) {
  this.requestHouseId = request.houseId?.let {
    HouseIdModel(it)
  } ?: HouseIdModel.NONE
}

fun BeContext.setQuery(request: IRequest) =
  when (request) {
    is RequestHouseRead -> setQuery(request)
    is RequestHouseCreate -> setQuery(request)
    else -> null
  }

private fun BeContext.setQuery(request: RequestHouseCreate) {
  request.createData?.let { data ->
    this.requestHouse = HouseModel(
      realtyType = RealtyTypeEnum.valueOf(data.realtyType.name),
      price = data.price ?: 0.0,
      area = data.area ?: 0.0,
      address = data.address ?: "",
      material = data.material?.let {
        HouseMaterialEnum.valueOf(
          it.name
        )
      } ?: HouseMaterialEnum.NONE,
      type = data.type?.let { HouseTypeEnum.valueOf(it.name) }
        ?: HouseTypeEnum.NONE,
      series = data.series ?: "",
      floors = data.floors ?: 0,
      areaPlot = data.areaPlot ?: 0.0,
      plotStatus = data.plotStatus?.let {
        PlotStatusEnum.valueOf(
          it.name
        )
      },
      infrastructure = data.infrastructure?.map {
        InfrastructureEnum.valueOf(
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
