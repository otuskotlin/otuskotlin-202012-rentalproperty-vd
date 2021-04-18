package ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.openapi.setQuery
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.HouseCreateDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.RequestHouseCreate
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.RequestHouseRead
import kotlin.test.Test
import kotlin.test.assertEquals

internal class HouseMappersTest {

  @Test
  fun requestIdMappingTest() {
    val request: IRequest = RequestHouseRead(
      houseId = "some-id"
    )
    val context = BeContext()

    context.setQuery(request)

    assertEquals("some-id", context.requestHouseId.id)
  }

  @Test
  fun requestCreateMappingTest() {
    val requestHouse: IRequest =
      RequestHouseCreate(
        createData = HouseCreateDto.STUB_SINGLE_HOUSE
      )
    val context = BeContext()

    context.setQuery(requestHouse)

    assertEquals(HouseTypeModel.STUB_SINGLE_HOUSE, context.requestHouse.type)
    assertEquals(HouseCreateDto.STUB_SINGLE_HOUSE.floors, context.requestHouse.floors)
  }
}
