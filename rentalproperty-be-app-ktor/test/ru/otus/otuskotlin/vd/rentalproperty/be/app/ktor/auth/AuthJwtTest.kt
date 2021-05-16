package ru.otus.otuskotlin.vd.rentalproperty.be.app.ktor.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

abstract class AuthJwtTest {
  companion object {
    const val OPTIONAL = "true"
    const val SECRET = "rp-app-secret"
    const val AUDIENCE = "test-rp-audience"
    const val REALM = "test-rp-realm"
    const val DOMAIN = "http://localhost/"
    const val USER_ID = "test-user-id"
    const val USER_FNAME = "Ivan"
    const val USER_MNAME = "Ivanovich"
    const val USER_LNAME = "Ivanov"
    val TOKEN = JWT.create()
      .withSubject("Authentication")
      .withIssuer(DOMAIN)
      .withAudience(AUDIENCE)
      .withClaim("id", USER_ID)
      .withClaim("fname", USER_FNAME)
      .withClaim("mname", USER_MNAME)
      .withClaim("lname", USER_LNAME)
//            .withExpiresAt(getExpiration())
      .sign(Algorithm.HMAC256(SECRET))
      .toString()
  }
}
