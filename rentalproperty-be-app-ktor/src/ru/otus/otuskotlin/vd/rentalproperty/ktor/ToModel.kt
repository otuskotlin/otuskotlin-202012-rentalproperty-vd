package ru.otus.otuskotlin.vd.rentalproperty.ktor

import kotlinx.serialization.SerializationException
import org.slf4j.LoggerFactory
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.Error

private val logger = LoggerFactory.getLogger("Throwable.toModel")
fun Throwable.toModel(): Error = when (this) {
    is SerializationException -> Error(message = "Request JSON syntax error: ${this.message}")
    is ClassCastException -> Error(message = "Wrong data sent to the endpoint: ${this.message}")
    else -> {
        logger.error("Unknown exception", this)
        Error(message = "Some exception is thrown: ${this.message}")
    }
}
