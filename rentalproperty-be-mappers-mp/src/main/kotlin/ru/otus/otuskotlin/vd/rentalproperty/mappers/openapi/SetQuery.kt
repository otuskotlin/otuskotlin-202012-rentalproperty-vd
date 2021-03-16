package ru.otus.otuskotlin.marketplace.mappers.openapi

import ru.otus.otuskotlin.marketplace.transport.kmp.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseMaterialModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.PlotStatusModel
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
      area = data.area ?: 0.0,
      address = data.address ?: "",
      material = data.material?.toModel() ?: HouseMaterialModel.NONE,
      type = data.type?.toModel() ?: HouseTypeModel.NONE,
      series = data.series ?: "",
      floors = data.floors ?: 0,
      areaPlot = data.areaPlot ?: 0.0,
      plotStatus = data.plotStatus?.toModel() ?: PlotStatusModel.NONE,
      infrastructure = data.infrastructure?.map { it.toModel() }
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
