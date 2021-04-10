package ru.otus.otuskotlin.vd.rentalproperty.be.common.context

enum class BeContextStatus {
  NONE,
  RUNNING,
  FINISHING,
  FAILING,
  SUCCESS,
  ERROR;

  val isError: Boolean
    get() = this in setOf(FAILING, ERROR)
}