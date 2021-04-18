package ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.mappers.openapi.setQuery
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.AppliancesDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.RequestDirectoryItemCreate
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.RequestDirectoryItemRead
import kotlin.test.Test
import kotlin.test.assertEquals

internal class DirectoryMappersTest {

  @Test
  fun requestIdMappingTest() {
    val request: IRequest = RequestDirectoryItemRead(
      directoryItem = AppliancesDto.STUB_AIR_CONDITIONER
    )
    val context = BeContext()

    context.setQuery(request)

    assertEquals(
      AppliancesDto.STUB_AIR_CONDITIONER.id,
      context.requestDirectoryItemId.id
    )
  }

  @Test
  fun requestCreateMappingTest() {
    val requestDirectoryItem: IRequest =
      RequestDirectoryItemCreate(
        directoryItem = AppliancesDto.STUB_AIR_CONDITIONER
      )
    val context = BeContext()

    context.setQuery(requestDirectoryItem)

    assertEquals(
      AppliancesDto.STUB_AIR_CONDITIONER.name,
      context.requestDirectoryItem.name
    )
  }
}
