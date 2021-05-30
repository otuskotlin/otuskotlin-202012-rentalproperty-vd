package ru.otus.otuskotlin.vd.rentalproperty.be.logging

import kotlinx.coroutines.runBlocking
import kotlin.test.Test

internal class LoggerTest {

  @Test
  fun loggerInit() {
    runBlocking {
      val logger = mpLogger(this::class.java)
      logger.doWithLoggingSusp(logId = "test-logger") {
        println("Some action")
      }
    }
  }
}
