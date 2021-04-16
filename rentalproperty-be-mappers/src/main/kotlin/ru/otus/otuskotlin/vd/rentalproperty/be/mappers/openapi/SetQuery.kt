package ru.otus.otuskotlin.vd.rentalproperty.be.mappers.openapi

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryItemIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend.toModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.flat.RequestAdvertFlatCreate
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.flat.RequestAdvertFlatRead
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.RequestAdvertHouseCreate
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.RequestAdvertHouseRead
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.RequestDirectoryItemCreate
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.RequestDirectoryItemRead
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.RequestFlatCreate
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.RequestFlatRead
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.RequestHouseCreate
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.RequestHouseRead

fun BeContext.setQuery(request: IRequest) =
  when (request) {
    is RequestFlatRead -> setQuery(request)
    is RequestFlatCreate -> setQuery(request)
    is RequestAdvertFlatRead -> setQuery(request)
    is RequestAdvertFlatCreate -> setQuery(request)

    is RequestHouseRead -> setQuery(request)
    is RequestHouseCreate -> setQuery(request)
    is RequestAdvertHouseRead -> setQuery(request)
    is RequestAdvertHouseCreate -> setQuery(request)

    is RequestDirectoryItemRead -> setQuery(request)
    is RequestDirectoryItemCreate -> setQuery(request)

    else -> null
  }

//Flat
private fun BeContext.setQuery(request: RequestFlatRead) {
  this.requestFlatId = request.flatId?.let {
    FlatIdModel(it)
  } ?: FlatIdModel.NONE
}

private fun BeContext.setQuery(request: RequestFlatCreate) {
  request.createData?.let { data ->
    this.requestFlat = data.toModel()
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
    this.requestAdvertFlat = data.toModel()
  }
}

//House
private fun BeContext.setQuery(request: RequestHouseRead) {
  this.requestHouseId = request.houseId?.let {
    HouseIdModel(it)
  } ?: HouseIdModel.NONE
}

private fun BeContext.setQuery(request: RequestHouseCreate) {
  request.createData?.let { data ->
    this.requestHouse = data.toModel()
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
    this.requestAdvertHouse = data.toModel()
  }
}

//Directory
private fun BeContext.setQuery(request: RequestDirectoryItemRead) {
  this.requestDirectoryItemId = request.directoryItemId?.let {
    DirectoryItemIdModel(it)
  } ?: DirectoryItemIdModel.NONE
}

private fun BeContext.setQuery(request: RequestDirectoryItemCreate) {
  when (request.directoryName) {
    "appliances" -> request.directoryItem?.let { data ->
      this.requestDirectoryItem = data.toModel()
    }
  }
}