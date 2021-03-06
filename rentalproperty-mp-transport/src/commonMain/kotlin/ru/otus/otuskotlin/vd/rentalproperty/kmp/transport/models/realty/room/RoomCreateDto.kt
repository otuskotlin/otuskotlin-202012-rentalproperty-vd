package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.room

import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.ConveniencesDto

@Serializable
class RoomCreateDto(
  val price: Double? = null,
  val area: Double? = null,
  val address: String? = null,
  val houseId: String? = null,
  val number: Int? = null,
  val ceilingHeight: Double? = null,
  val conveniences: MutableSet<ConveniencesDto>? = null,
)