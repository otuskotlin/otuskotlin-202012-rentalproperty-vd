package ru.otus.otuskotlin.vd.rentalproperty.kmp.logs

import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.FlatDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.HouseDto

data class LogDto(
  val requestFlatId: String? = null,
  val requestHouseId: String? = null,
  val requestFlat: FlatDto? = null,
  val requestHouse: HouseDto? = null,
  val responseFlat: FlatDto? = null,
  val responseHouse: HouseDto? = null,
  val responseFlats: List<FlatDto>? = null,
  val responseHouses: List<HouseDto>? = null,
)
