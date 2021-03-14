package ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums

import kotlinx.serialization.Serializable

/**
 * Виды ремонтов в квартире
 */
@Serializable
enum class RepairTypeEnum {
  WITHOUT_REPAIR,
  RENOVATION, //евроремонт
  DESIGNER_RENOVATION,  //дизайнерский ремонт
  REDECORATING,  //косметический ремонт
}
