package ru.otus.otuskotlin.vd.rentalproperty.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertRentHouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.advert.house.*
import java.time.Instant

internal fun AdvertRentHouseModel.toTransport() = AdvertRentHouseDto(
  id = id.id.takeIf { it.isNotBlank() },
  userId = userId.takeIf { it.id.isNotBlank() }?.id,
  houseId = houseId.takeIf { it.id.isNotBlank() }?.id,
  name = name.takeIf { it.isNotBlank() },
  description = description.takeIf { it.isNotBlank() },
  price = price.takeIf { !it.isNaN() },
  published = published.takeIf { it != null }?.toString(),
)

internal fun AdvertRentHouseDto.toModel() = AdvertRentHouseModel(
  id = id?.let { AdvertIdModel(it) } ?: AdvertIdModel.NONE,
  userId = userId?.let { UserIdModel(it) } ?: UserIdModel.NONE,
  houseId = houseId?.let { HouseIdModel(it) } ?: HouseIdModel.NONE,
  name = name ?: "",
  description = description ?: "",
  price = price ?: 0.0,
  published = published?.let { Instant.parse(published) },
)

fun BeContext.setQuery(query: RequestAdvertRentHouseCreate) = apply {
  requestAdvertRentHouse = query.createData?.toModel() ?: AdvertRentHouseModel.NONE
}

fun BeContext.setQuery(query: RequestAdvertRentHouseRead) = apply {
  requestAdvertRentHouseId = query.advertId?.let { AdvertIdModel(it) } ?: AdvertIdModel.NONE
}

fun BeContext.setQuery(query: RequestAdvertRentHouseUpdate) = apply {
  requestAdvertRentHouse = query.updateData?.toModel() ?: AdvertRentHouseModel.NONE
}

fun BeContext.setQuery(query: RequestAdvertRentHouseDelete) = apply {
  requestAdvertRentHouseId = query.advertId?.let { AdvertIdModel(it) } ?: AdvertIdModel.NONE
}

fun BeContext.setQuery(query: RequestAdvertRentHouseList) = apply {
  advertFilter = query.filterData?.let {
    AdvertFilterModel(
      text = it.text ?: ""
    )
  } ?: AdvertFilterModel.NONE
}

fun BeContext.respondAdvertRentHouseCreate() = ResponseAdvertRentHouseCreate(
  advert = responseAdvertRentHouse.takeIf { it != AdvertRentHouseModel.NONE }?.toTransport()
)

fun BeContext.respondAdvertRentHouseRead() = ResponseAdvertRentHouseRead(
  advert = responseAdvertRentHouse.takeIf { it != AdvertRentHouseModel.NONE }?.toTransport()
)

fun BeContext.respondAdvertRentHouseUpdate() = ResponseAdvertRentHouseUpdate(
  advert = responseAdvertRentHouse.takeIf { it != AdvertRentHouseModel.NONE }?.toTransport()
)

fun BeContext.respondAdvertRentHouseDelete() = ResponseAdvertRentHouseDelete(
  advert = responseAdvertRentHouse.takeIf { it != AdvertRentHouseModel.NONE }?.toTransport()
)

fun BeContext.respondAdvertRentHouseList() = ResponseAdvertRentHouseList(
  adverts = responseAdvertRentHouses.takeIf { it.isNotEmpty() }?.filter { it != AdvertRentHouseModel.NONE }
    ?.map { it.toTransport() }
)

private fun AdvertRentHouseCreateDto.toModel() = AdvertRentHouseModel(
  userId = userId?.let { UserIdModel(it) } ?: UserIdModel.NONE,
  houseId = houseId?.let { HouseIdModel(it) } ?: HouseIdModel.NONE,
  name = name ?: "",
  description = description ?: "",
  price = price ?: 0.0,
  published = published?.let { Instant.parse(published) },
)

private fun AdvertRentHouseUpdateDto.toModel() = AdvertRentHouseModel(
  id = id?.let { AdvertIdModel(it) } ?: AdvertIdModel.NONE,
  userId = userId?.let { UserIdModel(it) } ?: UserIdModel.NONE,
  houseId = houseId?.let { HouseIdModel(it) } ?: HouseIdModel.NONE,
  name = name ?: "",
  description = description ?: "",
  price = price ?: 0.0,
  published = published?.let { Instant.parse(published) },
)