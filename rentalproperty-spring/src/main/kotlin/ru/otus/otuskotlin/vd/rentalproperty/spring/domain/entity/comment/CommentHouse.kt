package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.comment

import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.realty.House
import ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model.Id
import java.time.Instant

data class CommentHouse(
  override var id: Id = Id.NONE,
  override var name: String,
  override var description: String,
  override var published: Instant? = null,
  override var rating: Double,
  var house: House,
) : Comment()