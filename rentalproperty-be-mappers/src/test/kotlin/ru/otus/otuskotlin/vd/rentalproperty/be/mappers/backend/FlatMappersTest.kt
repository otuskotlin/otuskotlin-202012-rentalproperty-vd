package ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.RepairTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.openapi.setQuery
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.FlatCreateDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.RequestFlatCreate
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.RequestFlatRead
import kotlin.test.Test
import kotlin.test.assertEquals

internal class FlatMappersTest {

  @Test
  fun requestIdMappingTest() {
    val request: IRequest = RequestFlatRead(
      flatId = "some-id"
    )
    val context = BeContext()

    context.setQuery(request)

    assertEquals("some-id", context.requestFlatId.id)
  }

  @Test
  fun requestCreateMappingTest() {
    val requestFlat: IRequest =
      RequestFlatCreate(
        createData = FlatCreateDto.STUB
      )
    val context = BeContext()

    context.setQuery(requestFlat)

    assertEquals(FlatCreateDto.STUB.area, context.requestFlat.area)
    assertEquals(FlatCreateDto.STUB.rooms, context.requestFlat.rooms)
    assertEquals(FlatCreateDto.STUB.floor, context.requestFlat.floor)
    assertEquals(
      RepairTypeModel.STUB_RENOVATION,
      context.requestFlat.repairType
    )
    assertEquals(FlatCreateDto.STUB.description, context.requestFlat.description)
  }
}
