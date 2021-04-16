package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.flat.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.*

val jsonConfig: Json by lazy {
    Json {
        prettyPrint = true
        serializersModule = SerializersModule {
            polymorphic(Message::class) {
              subclass(RequestFlatCreate::class)
              subclass(RequestFlatRead::class)
              subclass(RequestFlatDelete::class)
              subclass(RequestFlatUpdate::class)
              subclass(RequestFlatList::class)
              subclass(ResponseFlatCreate::class)
              subclass(ResponseFlatRead::class)
              subclass(ResponseFlatDelete::class)
              subclass(ResponseFlatUpdate::class)
              subclass(ResponseFlatList::class)

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

              subclass(RequestAdvertFlatCreate::class)
              subclass(RequestAdvertFlatRead::class)
              subclass(RequestAdvertFlatDelete::class)
              subclass(RequestAdvertFlatUpdate::class)
              subclass(RequestAdvertFlatList::class)
              subclass(ResponseAdvertFlatCreate::class)
              subclass(ResponseAdvertFlatRead::class)
              subclass(ResponseAdvertFlatDelete::class)
              subclass(ResponseAdvertFlatUpdate::class)
              subclass(ResponseAdvertFlatList::class)

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

              subclass(RequestDirectoryItemCreate::class)
              subclass(RequestDirectoryItemRead::class)
              subclass(RequestDirectoryItemDelete::class)
              subclass(RequestDirectoryItemUpdate::class)
              subclass(RequestDirectoryItemList::class)
              subclass(ResponseDirectoryItemCreate::class)
              subclass(ResponseDirectoryItemRead::class)
              subclass(ResponseDirectoryItemDelete::class)
              subclass(ResponseDirectoryItemUpdate::class)
              subclass(ResponseDirectoryItemList::class)
            }
        }
        classDiscriminator = "type"
    }
}
