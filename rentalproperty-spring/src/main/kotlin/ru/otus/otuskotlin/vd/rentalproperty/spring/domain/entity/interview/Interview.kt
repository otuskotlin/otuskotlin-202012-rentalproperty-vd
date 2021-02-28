package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.interview

import ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model.Id
import java.time.Instant

data class Interview(
  var id: Id,
  var name: String,
  var answer: Int,
  var published: Instant?
)