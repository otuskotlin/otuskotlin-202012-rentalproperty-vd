package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models

import kotlinx.serialization.json.Json
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.WorkModeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.HouseCreateDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.RequestHouseCreate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DebugSerializationTest {

  @Test
  fun debugSerialibzation() {
    val json = Json {
      prettyPrint = true
    }
    val dto = RequestHouseCreate(
      requestId = "create-id",
      startTime = "2021-02-13T12:00:00",
      createData = HouseCreateDto.STUB_SINGLE_HOUSE,
      debug = RequestHouseCreate.Debug(
        mode = WorkModeDto.TEST,
        stubCase = RequestHouseCreate.StubCase.SUCCESS
      )
    )

    val serializedString = json.encodeToString(RequestHouseCreate.serializer(), dto)
//    val serializedString = jsonConfig.encodeToString(Message.serializer(), dto)
    println(serializedString)
    assertTrue { serializedString.contains(Regex("stubCase\":\\s*\"SUCCESS")) }
    val deserializedDto = json.decodeFromString(RequestHouseCreate.serializer(), serializedString)
    assertEquals(RequestHouseCreate.StubCase.SUCCESS, deserializedDto.debug?.stubCase)
  }
}
