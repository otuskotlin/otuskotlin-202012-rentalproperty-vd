package ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums

import kotlinx.serialization.Serializable

/**
 * Возможные варианты статуса земельного участка
 */
@Serializable
enum class PlotStatusEnum {
  IRP,   //individual residential property
  GARDEN,
  FARM
}
