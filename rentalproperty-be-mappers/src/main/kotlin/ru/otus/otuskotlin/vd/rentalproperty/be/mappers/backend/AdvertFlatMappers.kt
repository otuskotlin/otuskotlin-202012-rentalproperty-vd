package ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertFlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.flat.*
import java.time.Duration
import java.time.Instant

internal fun AdvertFlatModel.toTransport() = AdvertFlatDto(
  id = id.id.takeIf { it.isNotBlank() },
  userId = userId.takeIf { it.id.isNotBlank() }?.id,
  flatId = flatId.takeIf { it.id.isNotBlank() }?.id,
  name = name.takeIf { it.isNotBlank() },
  description = description.takeIf { it.isNotBlank() },
  price = price.takeIf { !it.isNaN() },
  startDate = startDate.toString(),
  minPeriod = minPeriod.takeIf { !it.isZero }?.toDays()?.toInt(),
  published = published.takeIf { it != null }?.toString(),
)

fun BeContext.setQuery(query: RequestAdvertFlatCreate) = apply {
  requestAdvertFlat = query.createData?.toModel() ?: AdvertFlatModel.NONE
  stubCase = when (query.debug?.stubCase) {
    RequestAdvertFlatCreate.StubCase.SUCCESS -> StubCase.ADVERT_CREATE_SUCCESS
    else -> StubCase.NONE
  }
}

fun BeContext.setQuery(query: RequestAdvertFlatRead) = apply {
  requestAdvertFlatId = query.advertId?.let { AdvertIdModel(it) } ?: AdvertIdModel.NONE
  stubCase = when (query.debug?.stubCase) {
    RequestAdvertFlatRead.StubCase.SUCCESS -> StubCase.ADVERT_READ_SUCCESS
    else -> StubCase.NONE
  }
}

fun BeContext.setQuery(query: RequestAdvertFlatUpdate) = apply {
  requestAdvertFlat = query.updateData?.toModel() ?: AdvertFlatModel.NONE
  stubCase = when (query.debug?.stubCase) {
    RequestAdvertFlatUpdate.StubCase.SUCCESS -> StubCase.ADVERT_UPDATE_SUCCESS
    else -> StubCase.NONE
  }
}

fun BeContext.setQuery(query: RequestAdvertFlatDelete) = apply {
  requestAdvertFlatId = query.advertId?.let { AdvertIdModel(it) } ?: AdvertIdModel.NONE
  stubCase = when (query.debug?.stubCase) {
    RequestAdvertFlatDelete.StubCase.SUCCESS -> StubCase.ADVERT_DELETE_SUCCESS
    else -> StubCase.NONE
  }
}

fun BeContext.setQuery(query: RequestAdvertFlatList) = apply {
  advertHouseFilter = query.filterData?.let {
    AdvertFilterModel(
      text = it.text ?: ""
    )
  } ?: AdvertFilterModel.NONE
  stubCase = when (query.debug?.stubCase) {
    RequestAdvertFlatList.StubCase.SUCCESS -> StubCase.ADVERT_LIST_SUCCESS
    else -> StubCase.NONE
  }
}

fun BeContext.respondAdvertFlatCreate() = ResponseAdvertFlatCreate(
  advert = responseAdvertFlat.takeIf { it != AdvertFlatModel.NONE }?.toTransport(),
  errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
  status = status.toTransport(),
  responseId = responseId,
  onRequest = onRequest,
  endTime = Instant.now().toString()
)

fun BeContext.respondAdvertFlatRead() = ResponseAdvertFlatRead(
  advert = responseAdvertFlat.takeIf { it != AdvertFlatModel.NONE }?.toTransport(),
  errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
  status = status.toTransport(),
  responseId = responseId,
  onRequest = onRequest,
  endTime = Instant.now().toString()
)

fun BeContext.respondAdvertFlatUpdate() = ResponseAdvertFlatUpdate(
  advert = responseAdvertFlat.takeIf { it != AdvertFlatModel.NONE }?.toTransport(),
  errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
  status = status.toTransport(),
  responseId = responseId,
  onRequest = onRequest,
  endTime = Instant.now().toString()
)

fun BeContext.respondAdvertFlatDelete() = ResponseAdvertFlatDelete(
  advert = responseAdvertFlat.takeIf { it != AdvertFlatModel.NONE }?.toTransport(),
  errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
  status = status.toTransport(),
  responseId = responseId,
  onRequest = onRequest,
  endTime = Instant.now().toString()
)

fun BeContext.respondAdvertFlatList() = ResponseAdvertFlatList(
  adverts = responseAdvertFlats.takeIf { it.isNotEmpty() }?.filter { it != AdvertFlatModel.NONE }
    ?.map { it.toTransport() },
  errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
  status = status.toTransport(),
  responseId = responseId,
  onRequest = onRequest,
  endTime = Instant.now().toString()
)

private fun AdvertFlatCreateDto.toModel() = AdvertFlatModel(
  userId = userId?.let { UserIdModel(it) } ?: UserIdModel.NONE,
  flatId = flatId?.let { FlatIdModel(it) } ?: FlatIdModel.NONE,
  name = name ?: "",
  description = description ?: "",
  price = price ?: 0.0,
  startDate = startDate?.let { Instant.parse(it) } ?: Instant.now(),
  minPeriod = period?.let { Duration.ofDays(it.toLong()) } ?: Duration.ZERO,
  published = published?.let { Instant.parse(it) },
)

private fun AdvertFlatUpdateDto.toModel() = AdvertFlatModel(
  id = id?.let { AdvertIdModel(it) } ?: AdvertIdModel.NONE,
  userId = userId?.let { UserIdModel(it) } ?: UserIdModel.NONE,
  flatId = flatId?.let { FlatIdModel(it) } ?: FlatIdModel.NONE,
  name = name ?: "",
  description = description ?: "",
  price = price ?: 0.0,
  startDate = startDate?.let { Instant.parse(it) } ?: Instant.now(),
  minPeriod = period?.let { Duration.ofDays(it.toLong()) } ?: Duration.ZERO,
  published = published?.let { Instant.parse(it) },
)