package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.producer.Producer
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.services.*
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(
  testing: Boolean = false,
  kafkaTestConsumer: Consumer<String, String>? = null,
  kafkaTestProducer: Producer<String, String>? = null
) {

  val directoryCrud = DirectoryCrud()
  val flatCrud = FlatCrud()
  val houseCrud = HouseCrud()
  val advertFlatCrud = AdvertFlatCrud()
  val advertHouseCrud = AdvertHouseCrud()

  val directoryService = DirectoryService(directoryCrud)
  val flatService = FlatService(flatCrud)
  val houseService = HouseService(houseCrud)
  val advertFlatService = AdvertFlatService(advertFlatCrud)
  val advertHouseService = AdvertHouseService(advertHouseCrud)

  install(CORS) {
    method(HttpMethod.Options)
    method(HttpMethod.Put)
    method(HttpMethod.Delete)
    method(HttpMethod.Patch)
    header(HttpHeaders.Authorization)
    header("MyCustomHeader")
    allowCredentials = true
    anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
  }

  install(ContentNegotiation) {
    json(
      contentType = ContentType.Application.Json,
      json = jsonConfig,
    )
  }

  // Подключаем Websocket
  websocketEndpoints(
    flatService = flatService,
    houseService = houseService,
    advertFlatService = advertFlatService,
    advertHouseService = advertHouseService,
  )

  // Подключаем Kafka
  val brokers = environment.config.propertyOrNull("rentalproperty.kafka.brokers")?.getString()
  if (brokers != null) {
    kafkaEndpoints(
      brokers = brokers,
      kafkaConsumer = kafkaTestConsumer,
      kafkaProducer = kafkaTestProducer,
      flatService = flatService,
      houseService = houseService,
      advertFlatService = advertFlatService,
      advertHouseService = advertHouseService,
    )
  }

  routing {
    get("/") {
      call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
    }

    // Static feature. Try to access `/static/ktor_logo.svg`
    static("/static") {
      resources("static")
    }

    directoryRouting(directoryService)
    flatRouting(flatService)
    houseRouting(houseService)
    advertFlatRouting(advertFlatService)
    advertHouseRouting(advertHouseService)
  }
}

