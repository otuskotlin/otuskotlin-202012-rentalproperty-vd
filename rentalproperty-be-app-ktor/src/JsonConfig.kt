package com.example

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.*

val jsonConfig: Json by lazy {
    Json {
        prettyPrint = true
        serializersModule = SerializersModule {
            polymorphic(Message::class) {
                subclass(RequestHouseCreate::class)
                subclass(RequestHouseRead::class)
                subclass(RequestHouseDelete::class)
                subclass(RequestHouseUpdate::class)
                subclass(RequestHouseList::class)
                subclass(ResponseHouseCreate::class)
                subclass(ResponseHouseRead::class)
                subclass(ResponseHouseDelete::class)
                subclass(ResponseHouseUpdate::class)
                subclass(ResponseHouseList::class)
            }

        }
        classDiscriminator = "type"
    }
}
