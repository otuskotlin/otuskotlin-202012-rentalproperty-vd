package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpFlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.DealTypeEnum
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.RealtyTypeEnum
import java.time.Instant

data class AdvertRentFlat(
  var id: Long,
  var realtyType: RealtyTypeEnum = RealtyTypeEnum.FLAT,
  var dealType: DealTypeEnum = DealTypeEnum.RENT,
  var name: String,
  var published: Instant? = null,
  val userId: String,
  var flat: MpFlatModel,
)