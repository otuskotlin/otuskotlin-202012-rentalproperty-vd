package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.HouseMaterialEnum
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.HouseTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.HouseCreateDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.RequestHouseCreate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SerializationTest {

    @Test
    fun serializeRequestHouseCreateTest() {
        val json = Json {
            prettyPrint = true
        }
        val dto = RequestHouseCreate(
            requestId = "create-id",
            startTime = "2021-03-08T00:10:24",
            createData = HouseCreateDto(
                price = 10_000_000.0,
                material = HouseMaterialEnum.BRICK,
                type = HouseTypeEnum.SINGLE_HOUSE,
                floors = 2,
                areaPlot = 10.0
            )
        )

        val serializedString = json.encodeToString(RequestHouseCreate.serializer(), dto)
        println(serializedString)
        assertTrue { serializedString.contains("BRICK") }
        val deserializedDto = json.decodeFromString(RequestHouseCreate.serializer(), serializedString)
        assertEquals(HouseTypeEnum.SINGLE_HOUSE, deserializedDto.createData?.type)
    }

    @Test
    fun serializeRequestTest() {
        val jsonRequest = Json {
            prettyPrint = true
            serializersModule = SerializersModule {
                polymorphic(Message::class) {
                    subclass(RequestHouseCreate::class, RequestHouseCreate.serializer())
                }

            }
            classDiscriminator = "type"
        }
        val dto: Message = RequestHouseCreate(
            requestId = "create-id",
            startTime = "2021-02-13T12:00:00",
            createData = HouseCreateDto(
                price = 5_000_000.0,
                material = HouseMaterialEnum.PANEL,
                type = HouseTypeEnum.PART_HOUSE,
                floors = 2,
                areaPlot = 4.0
            )
        )
        val serializedString = jsonRequest.encodeToString(dto)
        println(serializedString)
        assertTrue { serializedString.contains("PANEL") }
        val deserializedDto = jsonRequest.decodeFromString(Message.serializer(), serializedString)
        assertEquals(HouseTypeEnum.PART_HOUSE, (deserializedDto as? RequestHouseCreate)?.createData?.type)
    }

}
