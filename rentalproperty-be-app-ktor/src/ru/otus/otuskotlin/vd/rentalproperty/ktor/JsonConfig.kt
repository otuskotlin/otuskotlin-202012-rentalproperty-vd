package ru.otus.otuskotlin.vd.rentalproperty.ktor

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.advert.house.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.realty.house.*

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

                subclass(RequestAdvertRentHouseCreate::class)
                subclass(RequestAdvertRentHouseRead::class)
                subclass(RequestAdvertRentHouseDelete::class)
                subclass(RequestAdvertRentHouseUpdate::class)
                subclass(RequestAdvertRentHouseList::class)
                subclass(ResponseAdvertRentHouseCreate::class)
                subclass(ResponseAdvertRentHouseRead::class)
                subclass(ResponseAdvertRentHouseDelete::class)
                subclass(ResponseAdvertRentHouseUpdate::class)
                subclass(ResponseAdvertRentHouseList::class)
            }

        }
        classDiscriminator = "type"
    }
}
