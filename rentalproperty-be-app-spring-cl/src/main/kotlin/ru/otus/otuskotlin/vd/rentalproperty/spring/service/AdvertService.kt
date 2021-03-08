package ru.otus.otuskotlin.vd.rentalproperty.spring.service

import org.springframework.stereotype.Service
import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.advert.AdvertRentFlat
import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.response.AdvertRentFlatRequest

@Service
interface AdvertService {

  fun create(advertRentFlatRequest: AdvertRentFlatRequest): AdvertRentFlat

}