package ru.otus.otuskotlin.vd.rentalproperty.ktor.service

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertRentHouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserIdModel
import ru.otus.otuskotlin.vd.rentalproperty.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.advert.house.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ResponseStatusDto

class AdvertRentHouseService {
  private val advert = AdvertRentHouseModel(
    id = AdvertIdModel("test-id"),
    userId = UserIdModel("test-user-id"),
    name = "Продаётся квартира",
    description = "Хорошая квартира",
    price = 1_500_000.0,
  )

  suspend fun get(query: RequestAdvertRentHouseRead): Message = BeContext().run {
    try {
      setQuery(query)
      responseAdvertRentHouse = advert
      respondAdvertRentHouseGet().copy(
        responseId = "123",
        status = ResponseStatusDto.SUCCESS,
        onRequest = query.requestId
      )
    } catch (e: Throwable) {
      ResponseAdvertRentHouseRead(
        responseId = "123",
        onRequest = query.requestId,
        status = ResponseStatusDto.INTERNAL_SERVER_ERROR,
      )
    }
  }

  suspend fun create(query: RequestAdvertRentHouseCreate): Message = BeContext().run {
    try {
      setQuery(query)
      responseAdvertRentHouse = advert
      respondAdvertRentHouseCreate().copy(
        responseId = "123",
        status = ResponseStatusDto.SUCCESS,
        onRequest = query.requestId
      )
    } catch (e: Throwable) {
      ResponseAdvertRentHouseCreate(
        responseId = "123",
        onRequest = query.requestId,
        status = ResponseStatusDto.INTERNAL_SERVER_ERROR,
      )
    }
  }

  suspend fun update(query: RequestAdvertRentHouseUpdate): Message = BeContext().run {
    try {
      setQuery(query)
      responseAdvertRentHouse = advert
      respondAdvertRentHouseUpdate().copy(
        responseId = "123",
        status = ResponseStatusDto.SUCCESS,
        onRequest = query.requestId
      )
    } catch (e: Throwable) {
      ResponseAdvertRentHouseUpdate(
        responseId = "123",
        onRequest = query.requestId,
        status = ResponseStatusDto.INTERNAL_SERVER_ERROR,
      )
    }
  }

  suspend fun delete(query: RequestAdvertRentHouseDelete): Message = BeContext().run {
    try {
      setQuery(query)
      responseAdvertRentHouse = advert
      respondAdvertRentHouseDelete().copy(
        responseId = "123",
        status = ResponseStatusDto.SUCCESS,
        onRequest = query.requestId
      )
    } catch (e: Throwable) {
      ResponseAdvertRentHouseDelete(
        responseId = "123",
        onRequest = query.requestId,
        status = ResponseStatusDto.INTERNAL_SERVER_ERROR,
      )
    }
  }

  suspend fun filter(query: RequestAdvertRentHouseList): Message = BeContext().run {
    try {
      setQuery(query)
      responseAdvertRentHouses = mutableListOf(advert)
      respondAdvertRentHouseList().copy(
        responseId = "123",
        status = ResponseStatusDto.SUCCESS,
        onRequest = query.requestId
      )
    } catch (e: Throwable) {
      ResponseAdvertRentHouseList(
        responseId = "123",
        onRequest = query.requestId,
        status = ResponseStatusDto.INTERNAL_SERVER_ERROR,
      )
    }
  }

}
