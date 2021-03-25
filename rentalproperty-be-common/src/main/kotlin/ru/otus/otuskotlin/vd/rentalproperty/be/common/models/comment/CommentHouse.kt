package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.comment

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.comment.Comment
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.comment.CommentIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import java.time.Instant

data class CommentHouse(
  override val id: CommentIdModel = CommentIdModel.NONE,
  override val name: String = "",
  override val description: String = "",
  override val published: Instant? = null,
  override val rating: Double = 0.0,
  val houseId: HouseIdModel = HouseIdModel.NONE,
) : Comment()