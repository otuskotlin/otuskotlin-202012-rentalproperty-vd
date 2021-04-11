package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor

import io.ktor.config.*
import io.ktor.server.testing.*
import io.ktor.util.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import ru.datana.smart.common.ktor.kafka.TestConsumer
import ru.datana.smart.common.ktor.kafka.TestProducer
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.RequestHouseList
import java.time.Duration
import kotlin.test.Test
import kotlin.test.assertTrue

internal class KafkaTest {
  @OptIn(KtorExperimentalAPI::class)
  @Test
  fun test() {
    val consumer: TestConsumer<String, String> = TestConsumer(duration = Duration.ofMillis(20))
    val producer: TestProducer<String, String> = TestProducer()
    withTestApplication({
      (environment.config as MapApplicationConfig).apply {
        put("rentalproperty.kafka.topicIn", TOPIC_IN)
        put("rentalproperty.kafka.topicOut", TOPIC_OT)
        put("rentalproperty.kafka.brokers", BROKERS)
      }
      module(
        kafkaTestConsumer = consumer,
        kafkaTestProducer = producer,
      )
    }) {
      runBlocking {
        delay(60L)
        val requestJson = jsonConfig.encodeToString(
          Message.serializer(),
          RequestHouseList()
        )
        consumer.send(TOPIC_IN, "xx1", requestJson)

        delay(100L)

        val responseObjs = producer.getSent()
        assertTrue("Must contain two messages") {
          val feedBack = responseObjs.first().value()
          feedBack.contains(Regex("\"status\":\\s*\"SUCCESS\""))
        }
      }
    }
  }

  companion object {
    const val TOPIC_IN = "some-topic-in"
    const val TOPIC_OT = "some-topic-ot"
    const val BROKERS = ""
  }
}

