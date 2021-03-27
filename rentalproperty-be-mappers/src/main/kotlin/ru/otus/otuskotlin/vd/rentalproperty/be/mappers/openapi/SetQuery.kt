package ru.otus.otuskotlin.vd.rentalproperty.be.mappers.openapi

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertHouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseMaterialModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.PlotStatusModel
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.toModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.RequestAdvertHouseCreate
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.RequestAdvertHouseRead
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.RequestHouseCreate
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.RequestHouseRead
import java.time.Instant

fun BeContext.setQuery(request: IRequest) =
  when (request) {
    is RequestHouseRead -> setQuery(request)
    is RequestHouseCreate -> setQuery(request)
    is RequestAdvertHouseRead -> setQuery(request)
    is RequestAdvertHouseCreate -> setQuery(request)
    else -> null
  }

//House
private fun BeContext.setQuery(request: RequestHouseRead) {
  this.requestHouseId = request.houseId?.let {
    HouseIdModel(it)
  } ?: HouseIdModel.NONE
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

//AdvertRentHouse
private fun BeContext.setQuery(request: RequestAdvertHouseRead) {
  this.requestAdvertHouseId = request.advertId?.let {
    AdvertIdModel(it)
  } ?: AdvertIdModel.NONE
}

private fun BeContext.setQuery(request: RequestAdvertHouseCreate) {
  request.createData?.let { data ->
    this.requestAdvertHouse = AdvertHouseModel(
      userId = data.userId?.let { UserIdModel(it) } ?: UserIdModel.NONE,
      houseId = data.houseId?.let { HouseIdModel(it) } ?: HouseIdModel.NONE,
      name = data.name ?: "",
      description = data.description ?: "",
      price = data.price ?: 0.0,
      published = data.published?.let { Instant.parse(data.published) },
    )
  }
}