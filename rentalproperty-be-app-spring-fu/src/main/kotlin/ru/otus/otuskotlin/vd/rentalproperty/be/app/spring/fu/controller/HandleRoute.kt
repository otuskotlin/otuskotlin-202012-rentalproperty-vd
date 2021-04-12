package ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.fu.controller

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.body
import ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.fu.jsonConfig
import ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.fu.toModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import java.time.Instant
import java.util.*

@OptIn(InternalSerializationApi::class)
inline fun <reified T : Message, reified U : Message> handleRoute(
    request: ServerRequest,
    crossinline block: suspend BeContext.(T?) -> U
): ServerResponse = runBlocking {
    val ctx = BeContext(
        responseId = UUID.randomUUID().toString(),
        timeStarted = Instant.now()
    )
    try {
      val queryJson = request.body<String>()
      println("QUERY: $queryJson")
      val query = jsonConfig.decodeFromString(T::class.serializer(), queryJson) as T
      ctx.status = BeContextStatus.RUNNING
      val response = ctx.block(query)
      val responseJson = jsonConfig.encodeToString(U::class.serializer(), response)
      println("RESPONSE: $responseJson")
      ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(responseJson)
    } catch (e: Throwable) {
      ctx.status = BeContextStatus.FAILING
      ctx.errors.add(e.toModel())
      val response = ctx.block(null)
      val responseJson = jsonConfig.encodeToString(U::class.serializer(), response)
      println("RESPONSE ERR: $responseJson")
      ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(responseJson)
    }
}
