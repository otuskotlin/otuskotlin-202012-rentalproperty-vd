package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.kafka.flat

import io.ktor.config.*
import io.ktor.server.testing.*
import io.ktor.util.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import ru.datana.smart.common.ktor.kafka.TestConsumer
import ru.datana.smart.common.ktor.kafka.TestProducer
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.jsonConfig
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.module
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.RequestFlatList
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.ResponseFlatList
import java.time.Duration
import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class KafkaFlatListTest {
  @OptIn(KtorExperimentalAPI::class)
  @Test
  fun `get the list must be a success`() {
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
        val query = RequestFlatList(
          requestId = "123",
          startTime = Instant.now().toString(),
          debug = RequestFlatList.Debug(
            stubCase = RequestFlatList.StubCase.SUCCESS
          )
        )
        val requestJson = jsonConfig.encodeToString(Message.serializer(), query)
        consumer.send(TOPIC_IN, "xx1", requestJson)

        delay(100L)
        val responseObjs = producer.getSent()
        assertTrue(responseObjs.isNotEmpty())
        val responseJson = responseObjs.first().value()
        assertTrue("Must contain \"status\":\"SUCCESS\"") {
          responseJson.contains(Regex("\"status\":\\s*\"SUCCESS\""))
        }
        val response = jsonConfig.decodeFromString(Message.serializer(), responseJson) as ResponseFlatList
        assertEquals("123", response.onRequest)
        assertEquals(1, response.flats?.size)
      }
    }
  }

  companion object {
    const val TOPIC_IN = "some-topic-in"
    const val TOPIC_OUT = "some-topic-out"
    const val BROKERS = ""
  }
}

