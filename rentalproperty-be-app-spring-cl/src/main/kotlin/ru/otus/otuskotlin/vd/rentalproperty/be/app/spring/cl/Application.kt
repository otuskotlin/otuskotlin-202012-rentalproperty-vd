package ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.cl

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application

fun main(args: Array<String>) {
  runApplication<Application>(*args) {
    setBannerMode(Banner.Mode.OFF)
  }
}
