package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.interview

import ru.otus.otuskotlin.vd.rentalproperty.spring.dsl.model.Id

data class Info(
  var id: Id,
  var name: String,
  var rating: Double
)