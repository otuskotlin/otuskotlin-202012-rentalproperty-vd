package ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.fu.controller

import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.ServerResponse.ok
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ErrorDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ResponseStatusDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.HouseMaterialDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.HouseTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.*
import java.time.Instant

class HouseController {
  fun list(request: ServerRequest): ServerResponse {
    val query = request.body(RequestHouseList::class.java)
    val response = ResponseHouseList(
      responseId = "123",
      onRequest = query.requestId,
      endTime = Instant.now().toString(),
      status = ResponseStatusDto.SUCCESS,
      houses = listOf(
        mockRead("house001"),
        mockRead("house002"),
        mockRead("house003"),
        mockRead("house004"),
        mockRead("house005"),
      )
    )
    return ok().body(response)
  }

  fun create(request: ServerRequest): ServerResponse {
    val query = request.body(RequestHouseCreate::class.java)
    val response = ResponseHouseCreate(
      responseId = "123",
      onRequest = query.requestId,
      endTime = Instant.now().toString(),
      status = ResponseStatusDto.SUCCESS,
      house = mockUpdate(
        id = "house123",
        area = query.createData?.area,
        address = query.createData?.address,
        material = query.createData?.material,
        type = query.createData?.type,
        floors = query.createData?.floors,
        areaPlot = query.createData?.areaPlot,
      )
    )
    return ok().body(response)
  }

  fun read(request: ServerRequest): ServerResponse {
    val query = request.body(RequestHouseRead::class.java)
    val response = ResponseHouseRead(
      responseId = "123",
      onRequest = query.requestId,
      endTime = Instant.now().toString(),
      status = ResponseStatusDto.SUCCESS,
      house = mockRead(query.houseId ?: "")
    )
    return ok().body(response)
  }

  fun update(request: ServerRequest): ServerResponse {
    val query = request.body(RequestHouseUpdate::class.java)
    val id = query.updateData?.id
    val response = if (id != null)
      ResponseHouseUpdate(
        responseId = "123",
        onRequest = query.requestId,
        endTime = Instant.now().toString(),
        status = ResponseStatusDto.SUCCESS,
        house = mockUpdate(
          id = id,
          area = query.updateData!!.area,
          address = query.updateData!!.address,
          material = query.updateData!!.material,
          type = query.updateData!!.type,
          floors = query.updateData!!.floors,
          areaPlot = query.updateData!!.areaPlot,
        )
      )
    else
      ResponseHouseUpdate(
        responseId = "123",
        onRequest = query.requestId,
        endTime = Instant.now().toString(),
        status = ResponseStatusDto.SUCCESS,
        errors = listOf(
          ErrorDto(
            code = "wrong-id",
            group = "validation",
            field = "id",
            level = ErrorDto.Level.ERROR,
            message = "id of the house to be updated cannot be empty"
          )
        )
      )
    return ok().body(response)
  }

  fun delete(request: ServerRequest): ServerResponse {
    val query = request.body(RequestHouseDelete::class.java)
    return ok().body(
      ResponseHouseDelete(
        responseId = "123",
        onRequest = query.requestId,
        endTime = Instant.now().toString(),
        status = ResponseStatusDto.SUCCESS,
        house = mockRead(query.houseId ?: ""),
        deleted = true
      )
    )
  }

  companion object {
    fun mockUpdate(
      id: String,
      area: Double?,
      address: String?,
      material: HouseMaterialDto?,
      type: HouseTypeDto?,
      floors: Int?,
      areaPlot: Double?,
    ) = HouseDto(
      id = id,
      area = area,
      address = address,
      material = material,
      type = type,
      floors = floors,
      areaPlot = areaPlot,
    )

    fun mockRead(id: String) = mockUpdate(
      id = id,
      area = 200.0,
      address = "Moscow",
      material = HouseMaterialDto("id", "BRICK"),
      type = HouseTypeDto("id", "SINGLE_HOUSE"),
      floors = 2,
      areaPlot = 10.0,
    )
  }
}
