package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty

/**
 * Виды ремонтов в квартире
 */
enum class RepairTypeDto {
  WITHOUT_REPAIR,
  RENOVATION, //евроремонт
  DESIGNER_RENOVATION,  //дизайнерский ремонт
  REDECORATING,  //косметический ремонт
}
