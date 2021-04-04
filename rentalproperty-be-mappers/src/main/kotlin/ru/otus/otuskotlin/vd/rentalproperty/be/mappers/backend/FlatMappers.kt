package ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.BathroomTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.RepairTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.ViewFromWindowModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.*
import java.time.Instant

internal fun FlatModel.toTransport() = FlatDto(
  id = id.id.takeIf { it.isNotBlank() },
  houseId = houseId.id.takeIf { it.isNotBlank() },
  area = area.takeIf { it != 0.0 },
  areaLiving = areaLiving.takeIf { it != 0.0 },
  areaKitchen = areaKitchen.takeIf { it != 0.0 },
  rooms = rooms.takeIf { it != 0 },
  floor = floor.takeIf { it != 0 },
  ceilingHeight = ceilingHeight.takeIf { it != 0.0 },
  bedrooms = bedrooms.takeIf { it != 0 },
  beds = beds.takeIf { it != 0 },
  bathroom = bathroom.takeIf { it != 0 },
  bathroomType = bathroomType.toTransport(),
  balcony = balcony.takeIf { it != 0 },
  loggia = loggia.takeIf { it != 0 },
  repairType = repairType.toTransport(),
  viewFromWindow = viewFromWindow.toTransport(),
  conveniences = conveniences.takeIf { it.isNotEmpty() }
    ?.map { it.toTransport() }?.toSet(),
  appliances = appliances.takeIf { it.isNotEmpty() }
    ?.map { it.toTransport() }?.toSet(),
  residents = residents.takeIf { it != 0 },
  noSmoking = noSmoking.takeIf { it },
  noAnimals = noAnimals.takeIf { it },
  noChildren = noChildren.takeIf { it },
  noParties = noParties.takeIf { it },
  photos = photos.takeIf { it.isNotEmpty() }
    ?.filter { it != MediaFileModel.NONE }
    ?.map { it.toTransport() }?.toSet()
)

fun BeContext.setQuery(query: RequestFlatCreate) = setQuery(query) {
  requestFlat = query.createData?.toModel() ?: FlatModel.NONE
  stubCase = when (query.debug?.stubCase) {
    RequestFlatCreate.StubCase.SUCCESS -> StubCase.FLAT_CREATE_SUCCESS
    else -> StubCase.NONE
  }
}

fun BeContext.setQuery(query: RequestFlatRead) = apply {
  requestFlatId = query.flatId?.let { FlatIdModel(it) } ?: FlatIdModel.NONE
  stubCase = when (query.debug?.stubCase) {
    RequestFlatRead.StubCase.SUCCESS -> StubCase.FLAT_READ_SUCCESS
    else -> StubCase.NONE
  }
}

fun BeContext.setQuery(query: RequestFlatUpdate) = apply {
  requestFlat = query.updateData?.toModel() ?: FlatModel.NONE
  stubCase = when (query.debug?.stubCase) {
    RequestFlatUpdate.StubCase.SUCCESS -> StubCase.FLAT_UPDATE_SUCCESS
    else -> StubCase.NONE
  }
}

fun BeContext.setQuery(query: RequestFlatDelete) = apply {
  requestFlatId = query.flatId?.let { FlatIdModel(it) } ?: FlatIdModel.NONE
  stubCase = when (query.debug?.stubCase) {
    RequestFlatDelete.StubCase.SUCCESS -> StubCase.FLAT_DELETE_SUCCESS
    else -> StubCase.NONE
  }
}

fun BeContext.setQuery(query: RequestFlatList) = apply {
  flatFilter = query.filterData?.let {
    FlatFilterModel(
      text = it.text ?: ""
    )
  } ?: FlatFilterModel.NONE
  stubCase = when (query.debug?.stubCase) {
    RequestFlatList.StubCase.SUCCESS -> StubCase.FLAT_LIST_SUCCESS
    else -> StubCase.NONE
  }
}

fun BeContext.respondFlatCreate() =
  ResponseFlatCreate(
    house = responseFlat.takeIf { it != FlatModel.NONE }?.toTransport(),
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    status = status.toTransport(),
    responseId = responseId,
    onRequest = onRequest,
    endTime = Instant.now().toString()
  )

fun BeContext.respondFlatRead() =
  ResponseFlatRead(
    house = responseFlat.takeIf { it != FlatModel.NONE }?.toTransport(),
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    status = status.toTransport(),
    responseId = responseId,
    onRequest = onRequest,
    endTime = Instant.now().toString()
  )

fun BeContext.respondFlatUpdate() =
  ResponseFlatUpdate(
    house = responseFlat.takeIf { it != FlatModel.NONE }?.toTransport(),
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    status = status.toTransport(),
    responseId = responseId,
    onRequest = onRequest,
    endTime = Instant.now().toString()
  )

fun BeContext.respondFlatDelete() =
  ResponseFlatDelete(
    house = responseFlat.takeIf { it != FlatModel.NONE }?.toTransport(),
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    status = status.toTransport(),
    responseId = responseId,
    onRequest = onRequest,
    endTime = Instant.now().toString()
  )

fun BeContext.respondFlatList() =
  ResponseFlatList(
    houses = responseFlats.takeIf { it.isNotEmpty() }?.filter { it != FlatModel.NONE }
      ?.map { it.toTransport() },
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    status = status.toTransport(),
    responseId = responseId,
    onRequest = onRequest,
    endTime = Instant.now().toString()
  )

private fun FlatCreateDto.toModel() = FlatModel(
  houseId = houseId?.let { HouseIdModel(it) }
    ?: HouseIdModel.NONE,
  area = area ?: 0.0,
  areaLiving = areaLiving ?: 0.0,
  areaKitchen = areaKitchen ?: 0.0,
  rooms = rooms ?: 0,
  floor = floor ?: 0,
  ceilingHeight = ceilingHeight ?: 0.0,
  residents = residents ?: 0,
  bedrooms = bedrooms ?: 0,
  beds = beds ?: 0,
  bathroom = bathroom ?: 0,
  bathroomType = bathroomType?.toModel() ?: BathroomTypeModel.NONE,
  balcony = balcony ?: 0,
  loggia = loggia ?: 0,
  repairType = repairType?.toModel() ?: RepairTypeModel.NONE,
  viewFromWindow = viewFromWindow?.toModel() ?: ViewFromWindowModel.NONE,
  conveniences = conveniences?.map { it.toModel() }
    ?.toMutableSet() ?: mutableSetOf(),
  appliances = appliances?.map { it.toModel() }
    ?.toMutableSet() ?: mutableSetOf(),
  noSmoking = noSmoking ?: false,
  noAnimals = noAnimals ?: false,
  noChildren = noChildren ?: false,
  noParties = noParties ?: false,
  description = description ?: "",
  photos = photos?.map { it.toModel() }?.toMutableSet() ?: mutableSetOf(),
)

private fun FlatUpdateDto.toModel() = FlatModel(
  id = id?.let { FlatIdModel(it) }
    ?: FlatIdModel.NONE,
  houseId = houseId?.let { HouseIdModel(it) }
    ?: HouseIdModel.NONE,
  area = area ?: 0.0,
  areaLiving = areaLiving ?: 0.0,
  areaKitchen = areaKitchen ?: 0.0,
  rooms = rooms ?: 0,
  floor = floor ?: 0,
  ceilingHeight = ceilingHeight ?: 0.0,
  residents = residents ?: 0,
  bedrooms = bedrooms ?: 0,
  beds = beds ?: 0,
  bathroom = bathroom ?: 0,
  bathroomType = bathroomType?.toModel() ?: BathroomTypeModel.NONE,
  balcony = balcony ?: 0,
  loggia = loggia ?: 0,
  repairType = repairType?.toModel() ?: RepairTypeModel.NONE,
  viewFromWindow = viewFromWindow?.toModel() ?: ViewFromWindowModel.NONE,
  conveniences = conveniences?.map { it.toModel() }
    ?.toMutableSet() ?: mutableSetOf(),
  appliances = appliances?.map { it.toModel() }
    ?.toMutableSet() ?: mutableSetOf(),
  noSmoking = noSmoking ?: false,
  noAnimals = noAnimals ?: false,
  noChildren = noChildren ?: false,
  noParties = noParties ?: false,
  description = description ?: "",
  photos = photos?.map { it.toModel() }?.toMutableSet() ?: mutableSetOf(),
)