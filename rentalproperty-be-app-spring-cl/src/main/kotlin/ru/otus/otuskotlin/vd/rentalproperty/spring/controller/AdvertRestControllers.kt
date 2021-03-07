package ru.otus.otuskotlin.vd.rentalproperty.spring.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.response.AdvertRentFlatRequest
import ru.otus.otuskotlin.vd.rentalproperty.spring.service.AdvertService

@RestController
@RequestMapping("/advert")
class AdvertRestController(private val advertService: AdvertService) {

  @PostMapping
  fun find(advertRentFlatRequest: AdvertRentFlatRequest) = advertService.create(advertRentFlatRequest)

}
