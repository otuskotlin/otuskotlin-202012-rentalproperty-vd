package ru.otus.otuskotlin.vd.rentalproperty.spring.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("app")
data class AppProperties(
  var title: String,
  val banner: Banner
) {

  data class Banner(
    val title: String? = null,
    val content: String
  )
}
