package ru.otus.otuskotlin.vd.rentalproperty.ktor.service

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseMaterialModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ResponseStatusDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.realty.house.*

class HouseService {
  private val house = HouseModel(
    id = HouseIdModel("test-id"),
    area = 100.0,
    address = "Novosibirsk",
    material = HouseMaterialModel(DirectoryIdModel("id"), "BRICK"),
    type = HouseTypeModel(DirectoryIdModel("id"), "SINGLE_HOUSE"),
    floors = 1,
    areaPlot = 5.0,
  )

  suspend fun get(query: RequestHouseRead): Message = BeContext().run {
    try {
      setQuery(query)
      responseHouse = house
      respondHouseGet().copy(
        responseId = "123",
        status = ResponseStatusDto.SUCCESS,
        onRequest = query.requestId
      )
    } catch (e: Throwable) {
      ResponseHouseRead(
        responseId = "123",
        onRequest = query.requestId,
        status = ResponseStatusDto.INTERNAL_SERVER_ERROR,
      )
    }
  }

  suspend fun create(query: RequestHouseCreate): Message = BeContext().run {
    try {
      setQuery(query)
      responseHouse = house
      respondHouseCreate().copy(
        responseId = "123",
        status = ResponseStatusDto.SUCCESS,
        onRequest = query.requestId
      )
    } catch (e: Throwable) {
      ResponseHouseCreate(
        responseId = "123",
        onRequest = query.requestId,
        status = ResponseStatusDto.INTERNAL_SERVER_ERROR,
      )
    }
  }

  suspend fun update(query: RequestHouseUpdate): Message = BeContext().run {
    try {
      setQuery(query)
      responseHouse = house
      respondHouseUpdate().copy(
        responseId = "123",
        status = ResponseStatusDto.SUCCESS,
        onRequest = query.requestId
      )
    } catch (e: Throwable) {
      ResponseHouseUpdate(
        responseId = "123",
        onRequest = query.requestId,
        status = ResponseStatusDto.INTERNAL_SERVER_ERROR,
      )
    }
  }

  suspend fun delete(query: RequestHouseDelete): Message = BeContext().run {
    try {
      setQuery(query)
      responseHouse = house
      respondHouseDelete().copy(
        responseId = "123",
        status = ResponseStatusDto.SUCCESS,
        onRequest = query.requestId
      )
    } catch (e: Throwable) {
      ResponseHouseDelete(
        responseId = "123",
        onRequest = query.requestId,
        status = ResponseStatusDto.INTERNAL_SERVER_ERROR,
      )
    }
  }

  suspend fun filter(query: RequestHouseList): Message = BeContext().run {
    try {
      setQuery(query)
      responseHouses = mutableListOf(house)
      respondHouseList().copy(
        responseId = "123",
        status = ResponseStatusDto.SUCCESS,
        onRequest = query.requestId
      )
    } catch (e: Throwable) {
      ResponseHouseList(
        responseId = "123",
        onRequest = query.requestId,
        status = ResponseStatusDto.INTERNAL_SERVER_ERROR,
      )
    }
  }

}
