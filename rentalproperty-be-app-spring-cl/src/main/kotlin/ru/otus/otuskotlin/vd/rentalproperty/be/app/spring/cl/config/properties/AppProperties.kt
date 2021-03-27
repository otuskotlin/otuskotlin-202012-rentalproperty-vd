package ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.cl.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("app")
data class AppProperties(
  var title: String,
  val banner: Banner? = null
) {

  data class Banner(
    val title: String? = null,
  )
}
