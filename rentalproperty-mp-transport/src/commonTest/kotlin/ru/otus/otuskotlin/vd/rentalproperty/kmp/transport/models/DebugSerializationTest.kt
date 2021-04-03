package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models

import kotlinx.serialization.json.Json
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.WorkModeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.HouseMaterialDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.HouseTypeDto
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
      createData = HouseCreateDto(
        material = HouseMaterialDto(
          "id",
          "BRICK"
        ),
        type = HouseTypeDto("id", "SINGLE_HOUSE"),
        floors = 2,
        areaPlot = 10.0,
      ),
      debug = RequestHouseCreate.Debug(
        mode = WorkModeDto.TEST,
        stubCase = RequestHouseCreate.StubCase.SUCCESS
      )
    )

    val serializedString = jsonConfig.encodeToString(Message.serializer(), dto)
    println(serializedString)
    assertTrue { serializedString.contains("stubCase\":\"SUCCESS") }
    val deserializedDto = json.decodeFromString(RequestHouseCreate.serializer(), serializedString)
    assertEquals(RequestHouseCreate.StubCase.SUCCESS, deserializedDto.debug?.stubCase)
  }
}
