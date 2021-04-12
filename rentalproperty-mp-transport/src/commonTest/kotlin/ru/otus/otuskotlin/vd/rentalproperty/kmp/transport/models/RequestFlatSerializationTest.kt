package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.RepairTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.FlatCreateDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.RequestFlatCreate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RequestFlatSerializationTest {

  @Test
  fun serializeRequestFlatCreateTest() {
    val json = Json {
      prettyPrint = true
    }
    val dto = RequestFlatCreate(
      requestId = "create-id",
      startTime = "2021-03-08T00:10:24",
      createData = FlatCreateDto.STUB
    )

    val serializedString = json.encodeToString(
      RequestFlatCreate.serializer(),
      dto
    )
    println(serializedString)
    assertTrue { serializedString.contains("Хрущёвка") }
    val deserializedDto = json.decodeFromString(RequestFlatCreate.serializer(), serializedString)
    assertEquals(
      RepairTypeDto.STUB_RENOVATION,
      (deserializedDto as? RequestFlatCreate)?.createData?.repairType
    )
  }

  @Test
  fun serializeRequestTest() {
    val jsonRequest = Json {
      prettyPrint = true
      serializersModule = SerializersModule {
        polymorphic(Message::class) {
          subclass(
            RequestFlatCreate::class,
            RequestFlatCreate.serializer()
          )
        }
      }
      classDiscriminator = "type"
    }
    val dto: Message =
      RequestFlatCreate(
        requestId = "create-id",
        startTime = "2021-02-13T12:00:00",
        createData = FlatCreateDto.STUB
      )
    val serializedString = jsonRequest.encodeToString(dto)
    println(serializedString)
    assertTrue { serializedString.contains("Хрущёвка") }
    val deserializedDto = jsonRequest.decodeFromString(Message.serializer(), serializedString)
    assertEquals(
      RepairTypeDto.STUB_RENOVATION,
      (deserializedDto as? RequestFlatCreate)?.createData?.repairType
    )
  }
}
