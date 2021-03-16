package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.interview

import java.time.Instant

data class Interview(
  override val id: InfoIdModel = InfoIdModel.NONE,
  override val name: String = "",
  override val answer: String = "",
  override val published: Instant? = null,
) : Info()