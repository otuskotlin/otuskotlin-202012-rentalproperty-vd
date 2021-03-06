package ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.fu

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
      ignoreUnknownKeys = true
      serializersModule = SerializersModule {
        polymorphic(Message::class) {
          subclass(RequestHouseList::class)
          subclass(RequestHouseCreate::class)
          subclass(RequestHouseRead::class)
          subclass(RequestHouseUpdate::class)
          subclass(RequestHouseDelete::class)

          subclass(ResponseHouseList::class)
          subclass(ResponseHouseCreate::class)
          subclass(ResponseHouseRead::class)
          subclass(ResponseHouseDelete::class)
          subclass(ResponseHouseUpdate::class)

          subclass(RequestAdvertHouseList::class)
          subclass(RequestAdvertHouseCreate::class)
          subclass(RequestAdvertHouseRead::class)
          subclass(RequestAdvertHouseUpdate::class)
          subclass(RequestAdvertHouseDelete::class)

          subclass(ResponseAdvertHouseList::class)
          subclass(ResponseAdvertHouseCreate::class)
          subclass(ResponseAdvertHouseRead::class)
          subclass(ResponseAdvertHouseDelete::class)
          subclass(ResponseAdvertHouseUpdate::class)
            }
        }
        classDiscriminator = "type"
    }
}
