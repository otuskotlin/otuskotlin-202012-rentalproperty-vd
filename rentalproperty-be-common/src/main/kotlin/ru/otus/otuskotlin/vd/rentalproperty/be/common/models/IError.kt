package ru.otus.otuskotlin.vd.rentalproperty.be.common.models

interface IError {
  val code: String
  val group: Group
  val field: String
  val level: Level
  val message: String

  enum class Group(val alias: String) {
    NONE(""),
    SERVER("internal-server"),
    AUTH("auth"),
  }

  enum class Level(val weight: Int) {
    FATAL(90),
    ERROR(70),
    WARNING(40),
    INFO(20);

    val isError: Boolean
      get() = weight >= ERROR.weight

    val isWarning: Boolean
      get() = this == WARNING
  }
}