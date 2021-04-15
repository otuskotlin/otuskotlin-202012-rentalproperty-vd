package ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryItemIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.RepairTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.openapi.setQuery
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.RepairTypeDto
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
        createData = FlatCreateDto(
          area = 44.4,
          areaKitchen = 4.0,
          rooms = 2,
          floor = 3,
          repairType = RepairTypeDto("id", "RENOVATION"),
          noSmoking = true,
          description = "Хрущёвка",
        )
      )
    val context = BeContext()

    context.setQuery(requestFlat)

    assertEquals(44.4, context.requestFlat.area)
    assertEquals(2, context.requestFlat.rooms)
    assertEquals(3, context.requestFlat.floor)
    assertEquals(
      RepairTypeModel(
        DirectoryItemIdModel("id"), "RENOVATION"
      ),
      context.requestFlat.repairType
    )
    assertEquals("Хрущёвка", context.requestFlat.description)
  }
}
