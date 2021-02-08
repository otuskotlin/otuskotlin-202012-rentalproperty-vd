package ru.otus.otuskotlin.vd.rentalproperty.spring

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.otus.otuskotlin.vd.rentalproperty.spring.config.properties.AppProperties

@SpringBootApplication
@EnableConfigurationProperties(AppProperties::class)
class Application

fun main(args: Array<String>) {
  runApplication<Application>(*args) {
    setBannerMode(Banner.Mode.OFF)
  }
}
