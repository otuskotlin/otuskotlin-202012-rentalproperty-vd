package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.interview

import java.time.Instant

data class Interview(
  var id: String,
  var name: String,
  var answer: Int,
  var published: Instant?
)