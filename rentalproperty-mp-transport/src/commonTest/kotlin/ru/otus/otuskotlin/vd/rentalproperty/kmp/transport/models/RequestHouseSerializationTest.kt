package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.HouseTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.HouseCreateDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.RequestHouseCreate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RequestHouseSerializationTest {

  @Test
  fun serializeRequestHouseCreateTest() {
    val json = Json {
      prettyPrint = true
    }
    val dto = RequestHouseCreate(
      requestId = "create-id",
      startTime = "2021-03-08T00:10:24",
      createData = HouseCreateDto.STUB_SINGLE_HOUSE
    )

    val serializedString = json.encodeToString(
      RequestHouseCreate.serializer(),
      dto
    )
    println(serializedString)
    assertTrue { serializedString.contains("BRICK") }
    val deserializedDto = json.decodeFromString(RequestHouseCreate.serializer(), serializedString)
    assertEquals(
      HouseTypeDto.STUB_SINGLE_HOUSE,
      (deserializedDto as? RequestHouseCreate)?.createData?.type
    )
  }

  @Test
  fun serializeRequestTest() {
    val jsonRequest = Json {
      prettyPrint = true
      serializersModule = SerializersModule {
        polymorphic(Message::class) {
          subclass(
            RequestHouseCreate::class,
            RequestHouseCreate.serializer()
          )
        }

      }
      classDiscriminator = "type"
    }
    val dto: Message =
      RequestHouseCreate(
        requestId = "create-id",
        startTime = "2021-02-13T12:00:00",
        createData = HouseCreateDto.STUB_SINGLE_HOUSE
      )
    val serializedString = jsonRequest.encodeToString(dto)
    println(serializedString)
    assertTrue { serializedString.contains("PANEL") }
    val deserializedDto = jsonRequest.decodeFromString(Message.serializer(), serializedString)
    assertEquals(
      HouseTypeDto.STUB_SINGLE_HOUSE,
      (deserializedDto as? RequestHouseCreate)?.createData?.type
    )
  }
}
