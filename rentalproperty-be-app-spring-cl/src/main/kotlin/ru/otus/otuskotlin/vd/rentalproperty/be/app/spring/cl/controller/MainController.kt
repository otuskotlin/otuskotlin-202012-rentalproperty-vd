package ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.cl.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.cl.config.properties.AppProperties
import java.time.LocalDateTime

@Controller
class MainController(private val properties: AppProperties) {

  @GetMapping("/")
  fun blog(model: Model): String {
    model["title"] = properties.title
    return "main"
  }

  @GetMapping("/ping")
  fun ping(): String? {
    return LocalDateTime.now().toString()
  }
}