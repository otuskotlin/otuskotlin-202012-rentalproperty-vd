package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.helper

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.jsonConfig
import ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.toModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import java.time.Instant
import java.util.*

@OptIn(InternalSerializationApi::class)
suspend inline fun <reified T : IRequest, reified U : Message>
    PipelineContext<Unit, ApplicationCall>.handleRoute(
  block: BeContext.(T?) -> U
) {
    val ctx = BeContext(
        responseId = UUID.randomUUID().toString(),
        timeStarted = Instant.now()
    )
    try {
        val query = call.receive<Message>() as T
        ctx.status = BeContextStatus.RUNNING
        val response = ctx.block(query)
        val respJson = jsonConfig.encodeToString(Message::class.serializer(), response)
        call.respondText(respJson, contentType = ContentType.parse("application/json"))
    } catch (e: Throwable) {
        ctx.status = BeContextStatus.FAILING
        ctx.errors.add(e.toModel())
        val response = ctx.block(null)
        val respJson = jsonConfig.encodeToString(Message::class.serializer(), response)
        call.respondText(respJson, contentType = ContentType.parse("application/json"))
    }
}
