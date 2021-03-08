package ru.otus.otuskotlin.vd.rentalproperty.spring.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.response.RealtyFilterRequest
import ru.otus.otuskotlin.vd.rentalproperty.spring.service.RealtyService

@RestController
@RequestMapping("/realty")
class RealtyRestController(private val realtyService: RealtyService) {

  @PostMapping
  fun find(realtyFilterRequest: RealtyFilterRequest) = realtyService.get(realtyFilterRequest)

}
