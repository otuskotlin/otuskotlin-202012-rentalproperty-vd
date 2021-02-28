package ru.otus.otuskotlin.vd.rentalproperty.ktor.enums

/**
 * Виды ремонтов в квартире
 */
enum class RepairTypeEnum {
  WITHOUT_REPAIR,
  RENOVATION, //евроремонт
  DESIGNER_RENOVATION,  //дизайнерский ремонт
  REDECORATING,  //косметический ремонт
}
