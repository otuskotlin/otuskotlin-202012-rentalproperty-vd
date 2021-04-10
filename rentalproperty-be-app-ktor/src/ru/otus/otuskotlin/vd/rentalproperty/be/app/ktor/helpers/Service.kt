package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helpers

import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.isActive
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.AdvertFlatService
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.AdvertHouseService
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.FlatService
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.HouseService
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.flat.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.*

suspend fun service(
  context: BeContext,
  query: Message?,
  houseService: HouseService,
  flatService: FlatService,
  advertHouseService: AdvertHouseService,
  advertFlatService: AdvertFlatService,
): Message? = when (query) {
  is RequestHouseList -> houseService.list(context, query)
  is RequestHouseCreate -> houseService.create(context, query)
  is RequestHouseRead -> houseService.read(context, query)
  is RequestHouseUpdate -> houseService.update(context, query)
  is RequestHouseDelete -> houseService.delete(context, query)

  is RequestFlatList -> flatService.list(context, query)
  is RequestFlatCreate -> flatService.create(context, query)
  is RequestFlatRead -> flatService.read(context, query)
  is RequestFlatUpdate -> flatService.update(context, query)
  is RequestFlatDelete -> flatService.delete(context, query)

  is RequestAdvertHouseList -> advertHouseService.list(context, query)
  is RequestAdvertHouseCreate -> advertHouseService.create(context, query)
  is RequestAdvertHouseRead -> advertHouseService.read(context, query)
  is RequestAdvertHouseUpdate -> advertHouseService.update(context, query)
  is RequestAdvertHouseDelete -> advertHouseService.delete(context, query)

  is RequestAdvertFlatList -> advertFlatService.list(context, query)
  is RequestAdvertFlatCreate -> advertFlatService.create(context, query)
  is RequestAdvertFlatRead -> advertFlatService.read(context, query)
  is RequestAdvertFlatUpdate -> advertFlatService.update(context, query)
  is RequestAdvertFlatDelete -> advertFlatService.delete(context, query)

  else ->
    // В дальнейшем здесь должен оказаться чейн обработки ошибок, и других событий
    when {
      context.status == BeContextStatus.FAILING -> houseService.list(context, null)
      // Если содзана новая сессия
      (context.userSession.fwSession as? WebSocketSession)?.isActive == true -> houseService.list(
        context,
        RequestHouseList()
      )
      // Если удалена сессия
      (context.userSession.fwSession as? WebSocketSession)?.isActive == false -> null
      else -> null
    }
}
