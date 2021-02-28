package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.advert

import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.person.User
import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.realty.Flat
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.DealTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.spring.enums.RealtyTypeEnum
import java.time.Instant

data class AdvertRentFlat(
  override var id: Long,
  override var realtyType: RealtyTypeEnum = RealtyTypeEnum.FLAT,
  override var dealType: DealTypeEnum = DealTypeEnum.RENT,
  override var name: String,
  override var published: Instant? = null,
  override val user: User,
  var flat: Flat,
) : Advert()