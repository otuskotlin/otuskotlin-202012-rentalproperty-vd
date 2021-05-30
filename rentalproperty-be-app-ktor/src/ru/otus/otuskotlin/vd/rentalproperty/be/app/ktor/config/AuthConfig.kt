package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.config

import io.ktor.application.*
import io.ktor.util.*

data class AuthConfig(
  val name: String,
  val optional: Boolean,
  val secret: String,
  val audience: String,
  val domain: String,
  val realm: String,
) {
  @OptIn(KtorExperimentalAPI::class)
  constructor(environment: ApplicationEnvironment) : this(
    name = environment.config.propertyOrNull("$PATH.name")
      ?.getString()
      ?: "auth-jwt",
    optional = environment.config.propertyOrNull("$PATH.optional")
      ?.getString()
      ?.toBoolean()
      ?: false,
    secret = environment.config.propertyOrNull("$PATH.secret")
      ?.getString()
      ?: "app-secret",
    audience = environment.config.propertyOrNull("$PATH.audience")
      ?.getString()
      ?: "users",
    domain = environment.config.propertyOrNull("$PATH.domain")
      ?.getString()
      ?: "http://localhost/",
    realm = environment.config.propertyOrNull("$PATH.realm")
      ?.getString()
      ?: "Application",
  )

  companion object {
    const val PATH = "rentalproperty.auth.jwt"
  }
}
