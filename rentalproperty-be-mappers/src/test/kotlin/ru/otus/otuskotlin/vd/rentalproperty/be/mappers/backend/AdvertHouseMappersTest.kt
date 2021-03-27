package ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.openapi.setQuery
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.AdvertHouseCreateDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.RequestAdvertHouseCreate
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.RequestAdvertHouseRead
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IRequest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class AdvertHouseMappersTest {

  @Test
  fun requestIdMappingTest() {
    val request: IRequest =
      RequestAdvertHouseRead(
        advertId = "test-id"
      )
    val context = BeContext()

    context.setQuery(request)

    assertEquals("test-id", context.requestAdvertHouseId.id)
  }

  @Test
  fun requestCreateMappingTest() {
    val requestHouse: IRequest =
      RequestAdvertHouseCreate(
        createData = AdvertHouseCreateDto(
          userId = "test-user-id",
          name = "Продаётся дом",
          description = "Хороший дом",
          price = 1_500_000.0,
        )
      )
    val context = BeContext()

    context.setQuery(requestHouse)

    assertEquals("Продаётся дом", context.requestAdvertHouse.name)
    assertEquals(1_500_000.0, context.requestAdvertHouse.price)
  }
}