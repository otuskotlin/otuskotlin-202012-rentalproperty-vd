package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.comment

import ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model.Id
import java.time.Instant

abstract class Comment {
  abstract var id: Id
  abstract var name: String
  abstract var description: String
  abstract var published: Instant?
  abstract var rating: Double
}