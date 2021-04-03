package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.*

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

        subclass(RequestAdvertHouseCreate::class)
        subclass(RequestAdvertHouseRead::class)
        subclass(RequestAdvertHouseDelete::class)
        subclass(RequestAdvertHouseUpdate::class)
        subclass(RequestAdvertHouseList::class)
        subclass(ResponseAdvertHouseCreate::class)
        subclass(ResponseAdvertHouseRead::class)
        subclass(ResponseAdvertHouseDelete::class)
        subclass(ResponseAdvertHouseUpdate::class)
        subclass(ResponseAdvertHouseList::class)
      }
    }
    classDiscriminator = "type"
  }
}
