package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.controller

import io.ktor.application.*
import io.ktor.routing.*
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import ru.datana.smart.common.ktor.kafka.KtorKafkaConsumer
import ru.datana.smart.common.ktor.kafka.kafka
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helper.service
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.jsonConfig
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service.AdvertFlatService
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service.AdvertHouseService
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service.FlatService
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.service.HouseService
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.toModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IResponse
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import java.time.Instant
import java.util.*

fun Application.kafkaEndpoints(
  brokers: String,
  kafkaConsumer: Consumer<String, String>?,
  kafkaProducer: Producer<String, String>?,
  flatService: FlatService,
  houseService: HouseService,
  advertFlatService: AdvertFlatService,
  advertHouseService: AdvertHouseService,
) {
  val topicIn by lazy { environment.config.property("rentalproperty.kafka.topicIn").getString() }
  val topicOut by lazy { environment.config.property("rentalproperty.kafka.topicOut").getString() }
  val producer: Producer<String, String> = kafkaProducer ?: run {
    KafkaProducer(
      mapOf(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to brokers,
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
      )
    )
  }

  install(KtorKafkaConsumer)

  routing {
    kafkaController(
      topicIn,
      topicOut,
      kafkaConsumer = kafkaConsumer,
      kafkaProducer = producer,
      flatService = flatService,
      houseService = houseService,
      advertFlatService = advertFlatService,
      advertHouseService = advertHouseService,
    )
  }
}

fun Routing.kafkaController(
  topicIn: String,
  topicOut: String,
  kafkaConsumer: Consumer<String, String>?,
  kafkaProducer: Producer<String, String>,
  flatService: FlatService,
  houseService: HouseService,
  advertFlatService: AdvertFlatService,
  advertHouseService: AdvertHouseService,
) {
  kafka<String, String> {
    keyDeserializer = StringDeserializer::class.java
    valDeserializer = StringDeserializer::class.java
    consumer = kafkaConsumer

    topic(topicIn) {
      println("GOT items")
      for (item in items.items) {
        val ctx = BeContext(
          responseId = UUID.randomUUID().toString(),
          timeStarted = Instant.now(),
        )
        try {
          ctx.status = BeContextStatus.RUNNING
          val query = jsonConfig.decodeFromString(Message.serializer(), item.value)
          service(
            context = ctx,
            query = query,
            flatService = flatService,
            houseService = houseService,
            advertFlatService = advertFlatService,
            advertHouseService = advertHouseService,
          )?.also {
            println("SEND SUCCESS to topic '$topicOut': $it")
            kafkaProducer.send(
              ProducerRecord(
                topicOut,
                (it as IResponse).responseId,
                jsonConfig.encodeToString(Message.serializer(), it)
              )
            )
          }
        } catch (e: Throwable) {
          e.printStackTrace()
          ctx.status = BeContextStatus.FAILING
          ctx.errors.add(e.toModel())
          service(
            context = ctx,
            query = null,
            flatService = flatService,
            houseService = houseService,
            advertFlatService = advertFlatService,
            advertHouseService = advertHouseService,
          )?.also {
            println("SEND ERROR to topic '$topicOut':  $it")
            kafkaProducer.send(
              ProducerRecord(
                topicOut,
                (it as IResponse).responseId,
                jsonConfig.encodeToString(Message.serializer(), it)
              )
            )
          }
        }
      }
    }
  }
}
