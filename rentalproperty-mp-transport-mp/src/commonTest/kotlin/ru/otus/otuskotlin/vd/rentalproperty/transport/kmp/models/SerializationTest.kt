package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.MpMessage
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.HouseMaterialDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.HouseTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.MpHouseCreateDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.MpRequestHouseCreate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SerializationTest {

  @Test
  fun serializeMpRequestHouseCreateTest() {
    val json = Json {
      prettyPrint = true
    }
    val dto = MpRequestHouseCreate(
      requestId = "create-id",
      startTime = "2021-03-08T00:10:24",
      createData = MpHouseCreateDto(
        price = 10_000_000.0,
        material = HouseMaterialDto.BRICK,
        type = HouseTypeDto.SINGLE_HOUSE,
        floors = 2,
        areaPlot = 10.0
      )
    )

    val serializedString = json.encodeToString(MpRequestHouseCreate.serializer(), dto)
    println(serializedString)
    assertTrue { serializedString.contains("BRICK") }
    val deserializedDto = json.decodeFromString(MpRequestHouseCreate.serializer(), serializedString)
    assertEquals(HouseTypeDto.SINGLE_HOUSE, deserializedDto.createData?.type)
  }

  @Test
  fun serializeMpRequestTest() {
    val jsonRequest = Json {
      prettyPrint = true
      serializersModule = SerializersModule {
        polymorphic(MpMessage::class) {
          subclass(MpRequestHouseCreate::class, MpRequestHouseCreate.serializer())
        }

      }
      classDiscriminator = "type"
    }
    val dto: MpMessage = MpRequestHouseCreate(
      requestId = "create-id",
      startTime = "2021-02-13T12:00:00",
      createData = MpHouseCreateDto(
        price = 5_000_000.0,
        material = HouseMaterialDto.PANEL,
        type = HouseTypeDto.PART_HOUSE,
        floors = 2,
        areaPlot = 4.0
      )
    )
    val serializedString = jsonRequest.encodeToString(dto)
    println(serializedString)
    assertTrue { serializedString.contains("PANEL") }
    val deserializedDto = jsonRequest.decodeFromString(MpMessage.serializer(), serializedString)
    assertEquals(HouseTypeDto.PART_HOUSE, (deserializedDto as? MpRequestHouseCreate)?.createData?.type)
  }

}
