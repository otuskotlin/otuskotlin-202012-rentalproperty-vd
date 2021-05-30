package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*

fun Application.featureAuth(authProperties: AuthProperties) {

  install(Authentication) {
    jwt(authProperties.name) {
      skipWhen { !authProperties.authOff }
      realm = authProperties.realm
      verifier(
        JWT
          .require(Algorithm.HMAC256(authProperties.secret))
          .withAudience(authProperties.audience)
          .withIssuer(authProperties.domain)
          .build()
      )
      validate { credential ->
        println(
          "AUDIENCE: ${credential.payload.audience} ${authProperties.audience} ${
            credential.payload.audience.contains(
              authProperties.audience
            )
          }"
        )
        println("ISSUER: ${credential.payload.issuer}")
        println("SUBJECT: ${credential.payload.subject}")
        if (credential.payload.audience.contains(authProperties.audience)) {
          JWTPrincipal(credential.payload)
        } else {
          null
        }
      }
    }
  }
}
