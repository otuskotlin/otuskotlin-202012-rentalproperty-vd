package controller

import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.ServerResponse.ok
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ErrorDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ResponseStatusDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.HouseMaterialDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.HouseTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.*
import java.time.Instant

class HouseController {
  fun list(request: ServerRequest): ServerResponse {
    val query = request.body(MpRequestHouseList::class.java)
    val response = MpResponseHouseList(
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
    val query = request.body(MpRequestHouseCreate::class.java)
    val response = MpResponseHouseCreate(
      responseId = "123",
      onRequest = query.requestId,
      endTime = Instant.now().toString(),
      status = ResponseStatusDto.SUCCESS,
      house = mockUpdate(
        id = "house123",
        price = query?.createData?.price,
        area = query?.createData?.area,
        address = query?.createData?.address,
        material = query?.createData?.material,
        type = query?.createData?.type,
        floors = query?.createData?.floors,
        areaPlot = query?.createData?.areaPlot,
      )
    )
    return ok().body(response)
  }

  fun read(request: ServerRequest): ServerResponse {
    val query = request.body(MpRequestHouseRead::class.java)
    val response = MpResponseHouseRead(
      responseId = "123",
      onRequest = query.requestId,
      endTime = Instant.now().toString(),
      status = ResponseStatusDto.SUCCESS,
      house = mockRead(query.houseId ?: "")
    )
    return ok().body(response)
  }

  fun update(request: ServerRequest): ServerResponse {
    val query = request.body(MpRequestHouseUpdate::class.java)
    val id = query.updateData?.id
    val response = if (id != null)
      MpResponseHouseUpdate(
        responseId = "123",
        onRequest = query.requestId,
        endTime = Instant.now().toString(),
        status = ResponseStatusDto.SUCCESS,
        house = mockUpdate(
          id = id,
          price = query?.updateData!!.price,
          area = query?.updateData!!.area,
          address = query?.updateData!!.address,
          material = query?.updateData!!.material,
          type = query?.updateData!!.type,
          floors = query?.updateData!!.floors,
          areaPlot = query?.updateData!!.areaPlot,
        )
      )
    else
      MpResponseHouseUpdate(
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
    val query = request.body(MpRequestHouseDelete::class.java)
    return ok().body(
      MpResponseHouseDelete(
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
      price: Double?,
      area: Double?,
      address: String?,
      material: HouseMaterialDto?,
      type: HouseTypeDto?,
      floors: Int?,
      areaPlot: Double?,
    ) = MpHouseDto(
      id = id,
      price = price,
      area = area,
      address = address,
      material = material,
      type = type,
      floors = floors,
      areaPlot = areaPlot,
    )

    fun mockRead(id: String) = mockUpdate(
      id = id,
      price = 10_000_000.0,
      area = 200.0,
      address = "Moscow",
      material = HouseMaterialDto.BRICK,
      type = HouseTypeDto.SINGLE_HOUSE,
      floors = 2,
      areaPlot = 10.0,
    )
  }
}
