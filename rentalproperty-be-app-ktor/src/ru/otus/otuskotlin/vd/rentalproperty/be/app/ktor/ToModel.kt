package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor

import io.ktor.auth.jwt.*
import kotlinx.serialization.SerializationException
import org.slf4j.LoggerFactory
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.Error
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.GrantedAuthority
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.PrincipalModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserModel
import kotlin.streams.toList

private val logger = LoggerFactory.getLogger("Throwable.toModel")
fun Throwable.toModel(): Error = when (this) {
  is SerializationException -> Error(message = "Request JSON syntax error: ${this.message}")
  is ClassCastException -> Error(message = "Wrong data sent to the endpoint: ${this.message}")
  else -> {
    logger.error("Unknown exception", this)
    Error(message = "Some exception is thrown: ${this.message}")
  }
}

fun JWTPrincipal.toModel(): PrincipalModel {
  val user = UserModel.STUB_USER //TODO get user from auth server or from db
  val authorities = mutableSetOf<GrantedAuthority>()
  authorities.addAll(user.roles.stream()
    .map { r -> GrantedAuthority("ROLE_" + r.name.toUpperCase()) }
    .toList())
  authorities.addAll(user.roles.stream()
    .flatMap { r -> r.privileges.stream() }
    .map { p -> GrantedAuthority(p.name) }
    .toList())
  return PrincipalModel(
    id = payload.getClaim("id")
      ?.asString()
      ?.let { UserIdModel(it) }
      ?: UserIdModel.NONE,
    user = user,
    authorities = authorities
  )
}
