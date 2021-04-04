package ru.otus.otuskotlin.vd.rentalproperty.be.mappers.openapi

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertFlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertHouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.*
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.toModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.flat.RequestAdvertFlatCreate
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.flat.RequestAdvertFlatRead
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.RequestAdvertHouseCreate
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.RequestAdvertHouseRead
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.RequestFlatCreate
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.RequestFlatRead
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.RequestHouseCreate
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.RequestHouseRead
import java.time.Duration
import java.time.Instant

fun BeContext.setQuery(request: IRequest) =
  when (request) {
    is RequestHouseRead -> setQuery(request)
    is RequestHouseCreate -> setQuery(request)
    is RequestAdvertHouseRead -> setQuery(request)
    is RequestAdvertHouseCreate -> setQuery(request)

    is RequestFlatRead -> setQuery(request)
    is RequestFlatCreate -> setQuery(request)
    is RequestAdvertFlatRead -> setQuery(request)
    is RequestAdvertFlatCreate -> setQuery(request)
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

//Flat
private fun BeContext.setQuery(request: RequestFlatRead) {
  this.requestFlatId = request.flatId?.let {
    FlatIdModel(it)
  } ?: FlatIdModel.NONE
}

private fun BeContext.setQuery(request: RequestFlatCreate) {
  request.createData?.let { data ->
    this.requestFlat = FlatModel(
      houseId = data.houseId?.let { HouseIdModel(it) }
        ?: HouseIdModel.NONE,
      area = data.area ?: 0.0,
      areaLiving = data.areaLiving ?: 0.0,
      areaKitchen = data.areaKitchen ?: 0.0,
      rooms = data.rooms ?: 0,
      floor = data.floor ?: 0,
      ceilingHeight = data.ceilingHeight ?: 0.0,
      residents = data.residents ?: 0,
      bedrooms = data.bedrooms ?: 0,
      beds = data.beds ?: 0,
      bathroom = data.bathroom ?: 0,
      bathroomType = data.bathroomType?.toModel() ?: BathroomTypeModel.NONE,
      balcony = data.balcony ?: 0,
      loggia = data.loggia ?: 0,
      repairType = data.repairType?.toModel() ?: RepairTypeModel.NONE,
      viewFromWindow = data.viewFromWindow?.toModel() ?: ViewFromWindowModel.NONE,
      conveniences = data.conveniences?.map { it.toModel() }
        ?.toMutableSet() ?: mutableSetOf(),
      appliances = data.appliances?.map { it.toModel() }
        ?.toMutableSet() ?: mutableSetOf(),
      noSmoking = data.noSmoking ?: false,
      noAnimals = data.noAnimals ?: false,
      noChildren = data.noChildren ?: false,
      noParties = data.noParties ?: false,
      description = data.description ?: "",
      photos = data.photos?.map { it.toModel() }?.toMutableSet() ?: mutableSetOf(),
    )
  }
}

//AdvertRentFlat
private fun BeContext.setQuery(request: RequestAdvertFlatRead) {
  this.requestAdvertFlatId = request.advertId?.let {
    AdvertIdModel(it)
  } ?: AdvertIdModel.NONE
}

private fun BeContext.setQuery(request: RequestAdvertFlatCreate) {
  request.createData?.let { data ->
    this.requestAdvertFlat = AdvertFlatModel(
      userId = data.userId?.let { UserIdModel(it) } ?: UserIdModel.NONE,
      flatId = data.flatId?.let { FlatIdModel(it) } ?: FlatIdModel.NONE,
      name = data.name ?: "",
      description = data.description ?: "",
      price = data.price ?: 0.0,
      startDate = data.startDate?.let { Instant.parse(it) } ?: Instant.now(),
      period = data.period?.let { Duration.ofDays(it.toLong()) } ?: Duration.ZERO,
      published = data.published?.let { Instant.parse(data.published) },
    )
  }
}