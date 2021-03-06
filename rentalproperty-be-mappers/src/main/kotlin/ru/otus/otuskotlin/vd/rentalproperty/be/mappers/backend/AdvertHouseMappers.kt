package ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.SortModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.WorkMode
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertHouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.*
import java.time.Instant

internal fun AdvertHouseModel.toTransport() =
  AdvertHouseDto(
    id = id.id.takeIf { it.isNotBlank() },
    userId = userId.takeIf { it.id.isNotBlank() }?.id,
    houseId = houseId.takeIf { it.id.isNotBlank() }?.id,
    name = name.takeIf { it.isNotBlank() },
    description = description.takeIf { it.isNotBlank() },
    price = price.takeIf { !it.isNaN() },
    published = published.takeIf { it != null }?.toString(),
  )

fun BeContext.setQuery(query: RequestAdvertHouseCreate) = apply {
  requestAdvertHouse = query.createData?.toModel() ?: AdvertHouseModel.NONE
  stubCase = when (query.debug?.stubCase) {
    RequestAdvertHouseCreate.StubCase.SUCCESS -> StubCase.ADVERT_CREATE_SUCCESS
    else -> StubCase.NONE
  }
  onRequest = query.requestId ?: ""
  workMode = query.debug?.mode.toModel()
}

fun BeContext.setQuery(query: RequestAdvertHouseRead) = apply {
  requestAdvertHouseId = query.advertId?.let { AdvertIdModel(it) } ?: AdvertIdModel.NONE
  stubCase = when (query.debug?.stubCase) {
    RequestAdvertHouseRead.StubCase.SUCCESS -> StubCase.ADVERT_READ_SUCCESS
    else -> StubCase.NONE
  }
  onRequest = query.requestId ?: ""
  workMode = query.debug?.mode.toModel()
}

fun BeContext.setQuery(query: RequestAdvertHouseUpdate) = apply {
  requestAdvertHouse = query.updateData?.toModel() ?: AdvertHouseModel.NONE
  stubCase = when (query.debug?.stubCase) {
    RequestAdvertHouseUpdate.StubCase.SUCCESS -> StubCase.ADVERT_UPDATE_SUCCESS
    else -> StubCase.NONE
  }
  onRequest = query.requestId ?: ""
  workMode = query.debug?.mode.toModel()
}

fun BeContext.setQuery(query: RequestAdvertHouseDelete) = apply {
  requestAdvertHouseId = query.advertId?.let { AdvertIdModel(it) } ?: AdvertIdModel.NONE
  stubCase = when (query.debug?.stubCase) {
    RequestAdvertHouseDelete.StubCase.SUCCESS -> StubCase.ADVERT_DELETE_SUCCESS
    else -> StubCase.NONE
  }
  onRequest = query.requestId ?: ""
  workMode = query.debug?.mode.toModel()
}

fun BeContext.setQuery(query: RequestAdvertHouseList) = apply {
  advertHouseFilter = query.filterData?.let {
    AdvertFilterModel(
      text = it.text ?: "",
      includeDescription = it.includeDescription ?: false,
      sortBy = it.sortBy?.let { SortModel.valueOf(it.name) } ?: SortModel.NONE,
      offset = it.offset ?: Int.MIN_VALUE,
      count = it.count ?: Int.MIN_VALUE,
    )
  } ?: AdvertFilterModel.NONE
  stubCase = when (query.debug?.stubCase) {
    RequestAdvertHouseList.StubCase.SUCCESS -> StubCase.ADVERT_LIST_SUCCESS
    else -> StubCase.NONE
  }
  onRequest = query.requestId ?: ""
  workMode = query.debug?.mode.toModel()
}

fun BeContext.respondAdvertHouseCreate() =
  ResponseAdvertHouseCreate(
    advert = responseAdvertHouse.takeIf { it != AdvertHouseModel.NONE }?.toTransport(),
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    status = status.toTransport(),
    responseId = responseId,
    onRequest = onRequest,
    endTime = Instant.now().toString(),
    debug = ResponseAdvertHouseCreate.Debug(
      mode = workMode.takeIf { it != WorkMode.DEFAULT }?.toTransport()
    )
  )

fun BeContext.respondAdvertHouseRead() =
  ResponseAdvertHouseRead(
    advert = responseAdvertHouse.takeIf { it != AdvertHouseModel.NONE }?.toTransport(),
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    status = status.toTransport(),
    responseId = responseId,
    onRequest = onRequest,
    endTime = Instant.now().toString(),
    debug = ResponseAdvertHouseRead.Debug(
      mode = workMode.takeIf { it != WorkMode.DEFAULT }?.toTransport()
    )
  )

fun BeContext.respondAdvertHouseUpdate() =
  ResponseAdvertHouseUpdate(
    advert = responseAdvertHouse.takeIf { it != AdvertHouseModel.NONE }?.toTransport(),
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    status = status.toTransport(),
    responseId = responseId,
    onRequest = onRequest,
    endTime = Instant.now().toString(),
    debug = ResponseAdvertHouseUpdate.Debug(
      mode = workMode.takeIf { it != WorkMode.DEFAULT }?.toTransport()
    )
  )

fun BeContext.respondAdvertHouseDelete() =
  ResponseAdvertHouseDelete(
    advert = responseAdvertHouse.takeIf { it != AdvertHouseModel.NONE }?.toTransport(),
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    status = status.toTransport(),
    responseId = responseId,
    onRequest = onRequest,
    endTime = Instant.now().toString(),
    debug = ResponseAdvertHouseDelete.Debug(
      mode = workMode.takeIf { it != WorkMode.DEFAULT }?.toTransport()
    )
  )

fun BeContext.respondAdvertHouseList() =
  ResponseAdvertHouseList(
    adverts = responseAdvertHouses.takeIf { it.isNotEmpty() }?.filter { it != AdvertHouseModel.NONE }
      ?.map { it.toTransport() },
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    status = status.toTransport(),
    responseId = responseId,
    onRequest = onRequest,
    endTime = Instant.now().toString(),
    debug = ResponseAdvertHouseList.Debug(
      mode = workMode.takeIf { it != WorkMode.DEFAULT }?.toTransport()
    )
  )

internal fun AdvertHouseCreateDto.toModel() = AdvertHouseModel(
  userId = userId?.let { UserIdModel(it) } ?: UserIdModel.NONE,
  houseId = houseId?.let { HouseIdModel(it) } ?: HouseIdModel.NONE,
  name = name ?: "",
  description = description ?: "",
  price = price ?: 0.0,
  published = published?.let { Instant.parse(it) },
)

private fun AdvertHouseUpdateDto.toModel() = AdvertHouseModel(
  id = id?.let { AdvertIdModel(it) } ?: AdvertIdModel.NONE,
  userId = userId?.let { UserIdModel(it) } ?: UserIdModel.NONE,
  houseId = houseId?.let { HouseIdModel(it) } ?: HouseIdModel.NONE,
  name = name ?: "",
  description = description ?: "",
  price = price ?: 0.0,
  published = published?.let { Instant.parse(it) },
)