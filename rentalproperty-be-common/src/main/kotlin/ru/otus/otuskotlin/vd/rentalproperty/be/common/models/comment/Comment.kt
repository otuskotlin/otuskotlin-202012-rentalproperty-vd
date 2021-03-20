package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.comment

import java.time.Instant

abstract class Comment {
  abstract val id: CommentIdModel
  abstract val name: String
  abstract val description: String
  abstract val published: Instant?
  abstract val rating: Double
}