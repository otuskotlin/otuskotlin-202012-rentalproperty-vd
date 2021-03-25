package ru.otus.otuskotlin.vd.rentalproperty.ktor.service

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.business.logic.backend.HouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.mappers.backend.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ResponseStatusDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.realty.house.*

class HouseService(
  private val crud: HouseCrud
) {

  suspend fun get(query: RequestHouseRead): Message = BeContext().run {
    try {
      crud.read(setQuery(query))
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
      crud.create(setQuery(query))
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
      crud.update(setQuery(query))
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
      crud.delete(setQuery(query))
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
      crud.filter(setQuery(query))
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
