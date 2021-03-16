package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.interview

import java.time.Instant

abstract class Info {
  abstract val id: InfoIdModel
  abstract val name: String
  abstract val answer: String
  abstract val published: Instant?
}