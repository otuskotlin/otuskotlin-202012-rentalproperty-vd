package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.producer.Producer
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.config.AuthProperties
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.config.CassandraProperties
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.config.featureAuth
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.config.featureRest
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller.*
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.exceptions.WrongConfigException
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service.*
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.*
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IDirectoryRepository
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IFlatRepository
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IHouseRepository
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.directory.DirectoryRepositoryCassandra
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.flats.FlatRepositoryCassandra
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.house.HouseRepositoryCassandra
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.directory.DirectoryRepoInMemory
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.realty.FlatRepoInMemory
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.realty.HouseRepoInMemory
import java.util.*
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@OptIn(ExperimentalTime::class, kotlin.ExperimentalStdlibApi::class)
@Suppress("unused") // Referenced in application.conf
fun Application.module(
  testing: Boolean = false,
  kafkaTestConsumer: Consumer<String, String>? = null,
  kafkaTestProducer: Producer<String, String>? = null,
  testDirectoryRepo: IDirectoryRepository? = null,
  testFlatRepo: IFlatRepository? = null,
  testHouseRepo: IHouseRepository? = null,
) {
  val authProperties by lazy { AuthProperties(environment) }
  featureAuth(authProperties)
  featureRest()

  val cassandraProperties by lazy { CassandraProperties(environment) }

  val repoProdName by lazy {
    environment.config.propertyOrNull("rentalproperty.repository.prod")
      ?.getString()
      ?.trim()
      ?.lowercase(Locale.getDefault())
      ?: "cassandra"
  }

  val directoryRepoProd = when (repoProdName) {
    "cassandra" -> DirectoryRepositoryCassandra(
      keyspaceName = cassandraProperties.keyspace,
      hosts = cassandraProperties.hosts,
      port = cassandraProperties.port,
      user = cassandraProperties.user,
      pass = cassandraProperties.pass,
    )
    "inmemory" -> DirectoryRepoInMemory()
    else -> throw WrongConfigException("Demand repository is not set")
  }

  val flatRepoProd = when (repoProdName) {
    "cassandra" -> FlatRepositoryCassandra(
      keyspaceName = cassandraProperties.keyspace,
      hosts = cassandraProperties.hosts,
      port = cassandraProperties.port,
      user = cassandraProperties.user,
      pass = cassandraProperties.pass,
    )
    "inmemory" -> FlatRepoInMemory()
    else -> throw WrongConfigException("Demand repository is not set")
  }

  val houseRepoProd = when (repoProdName) {
    "cassandra" -> HouseRepositoryCassandra(
      keyspaceName = cassandraProperties.keyspace,
      hosts = cassandraProperties.hosts,
      port = cassandraProperties.port,
      user = cassandraProperties.user,
      pass = cassandraProperties.pass,
    )
    "inmemory" -> HouseRepoInMemory()
    else -> throw WrongConfigException("Demand repository is not set")
  }

  val directoryRepoTest = testDirectoryRepo ?: DirectoryRepoInMemory(ttl = 2.toDuration(DurationUnit.HOURS))
  val flatRepoTest = testFlatRepo ?: FlatRepoInMemory(ttl = 2.toDuration(DurationUnit.HOURS))
  val houseRepoTest = testHouseRepo ?: HouseRepoInMemory(ttl = 2.toDuration(DurationUnit.HOURS))

  val directoryCrud = DirectoryCrud(
    directoryRepoTest = directoryRepoTest,
    directoryRepoProd = directoryRepoProd,
  )
  val flatCrud = FlatCrud(
    flatRepoTest = flatRepoTest,
    flatRepoProd = flatRepoProd
  )
  val houseCrud = HouseCrud(
    houseRepoTest = houseRepoTest,
    houseRepoProd = houseRepoProd
  )
  val advertFlatCrud = AdvertFlatCrud()
  val advertHouseCrud = AdvertHouseCrud()

  val directoryService = DirectoryService(directoryCrud)
  val flatService = FlatService(flatCrud)
  val houseService = HouseService(houseCrud)
  val advertFlatService = AdvertFlatService(advertFlatCrud)
  val advertHouseService = AdvertHouseService(advertHouseCrud)

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
    flatRouting(flatService, authProperties)
    houseRouting(houseService)
    advertFlatRouting(advertFlatService)
    advertHouseRouting(advertHouseService)
  }
}

