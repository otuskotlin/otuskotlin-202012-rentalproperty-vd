package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.comment

import java.time.Instant

abstract class Comment {
  abstract var id: String
  abstract var name: String
  abstract var description: String
  abstract var published: Instant?
  abstract var rating: Double
}