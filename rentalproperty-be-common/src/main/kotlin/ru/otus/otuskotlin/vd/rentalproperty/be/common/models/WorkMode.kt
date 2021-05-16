package ru.otus.otuskotlin.vd.rentalproperty.be.common.models

enum class WorkMode {
  PROD,
  TEST;

  companion object {
    val DEFAULT = PROD
  }
}
