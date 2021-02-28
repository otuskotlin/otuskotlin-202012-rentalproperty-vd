package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.advert

import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.person.User
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.DealTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.RealtyTypeEnum
import java.time.Instant

abstract class Advert {
  abstract var id: Long
  abstract var realtyType: RealtyTypeEnum
  abstract var dealType: DealTypeEnum
  abstract var name: String
  abstract var published: Instant?
  abstract val user: User
}