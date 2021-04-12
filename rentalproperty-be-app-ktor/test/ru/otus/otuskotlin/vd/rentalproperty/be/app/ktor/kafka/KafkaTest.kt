package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.kafka

import io.ktor.config.*
import io.ktor.server.testing.*
import io.ktor.util.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import ru.datana.smart.common.ktor.kafka.TestConsumer
import ru.datana.smart.common.ktor.kafka.TestProducer
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.jsonConfig
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.module
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IResponse
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ResponseStatusDto
import java.time.Duration
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class KafkaTest {
  @OptIn(KtorExperimentalAPI::class)
  @Test
  fun `bad json must fail`() {
    val consumer: TestConsumer<String, String> = TestConsumer(duration = Duration.ofMillis(20))
    val producer: TestProducer<String, String> = TestProducer()
    withTestApplication({
      (environment.config as MapApplicationConfig).apply {
        put("rentalproperty.kafka.topicIn", TOPIC_IN)
        put("rentalproperty.kafka.topicOut", TOPIC_OUT)
        put("rentalproperty.kafka.brokers", BROKERS)
      }
      module(
        kafkaTestConsumer = consumer,
        kafkaTestProducer = producer,
      )
    }) {
      runBlocking {
        delay(60L)
        val requestJson = """{"type":"123"}"""
        consumer.send(TOPIC_IN, "xx1", requestJson)

        delay(100L)
        val responseObjs = producer.getSent()
        val responseJson = responseObjs.first().value()
        assertTrue("Must contain \"status\":\"BAD_REQUEST\"") {
          responseJson.contains(Regex("\"status\":\\s*\"BAD_REQUEST\""))
        }
        val response = jsonConfig.decodeFromString(Message.serializer(), responseJson) as IResponse
        assertEquals(ResponseStatusDto.BAD_REQUEST, response.status)
      }
    }
  }

  companion object {
    const val TOPIC_IN = "some-topic-in"
    const val TOPIC_OUT = "some-topic-out"
    const val BROKERS = ""
  }
}

