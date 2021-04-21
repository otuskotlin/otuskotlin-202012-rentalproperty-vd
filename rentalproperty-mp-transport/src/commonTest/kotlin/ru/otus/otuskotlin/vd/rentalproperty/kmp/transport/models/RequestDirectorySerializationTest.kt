package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.DirectoryItemDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.IDirectoryDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.RequestDirectoryItemCreate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RequestDirectorySerializationTest {

  @Test
  fun serializeRequestDirectoryItemCreateTest() {
//    val json = Json {
//      prettyPrint = true
//    }
//    val dto = RequestDirectoryItemCreate(
//      requestId = "request-id",
//      startTime = "2021-03-08T00:10:24",
//      directoryItem = DirectoryItemDto.STUB
//    )
//
//    val serializedString = json.encodeToString(
//      RequestDirectoryItemCreate.serializer(),
//      dto
//    )
//    println(serializedString)
//    assertTrue { serializedString.contains("name item") }
//    val deserializedDto = json.decodeFromString(RequestDirectoryItemCreate.serializer(), serializedString)
//    assertEquals(
//      DirectoryItemDto.STUB.name,
//      (deserializedDto as? RequestDirectoryItemCreate)?.directoryItem?.name
//    )
  }

  @Test
  fun serializeRequestTest() {
    val json = Json {
      //ignoreUnknownKeys=true
      prettyPrint = true
      serializersModule = SerializersModule {
        polymorphic(IDirectoryDto::class) {
          subclass(DirectoryItemDto::class)
        }
        polymorphic(Message::class) {
          subclass(
            RequestDirectoryItemCreate::class,
            RequestDirectoryItemCreate.serializer()
          )
        }
      }
      classDiscriminator = "type"
    }
    val dto: Message =
      RequestDirectoryItemCreate(
        requestId = "request-id",
        startTime = "2021-02-13T12:00:00",
        directoryItem = DirectoryItemDto.STUB
      )
    val serializedString = json.encodeToString(dto)
    println(serializedString)
    assertTrue { serializedString.contains("name item") }
    val deserializedDto = json.decodeFromString(Message.serializer(), serializedString)
    assertEquals(
      DirectoryItemDto.STUB.name,
      (deserializedDto as? RequestDirectoryItemCreate)?.directoryItem?.name
    )
  }
}
