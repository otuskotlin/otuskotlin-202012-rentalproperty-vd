package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common

interface IFilterItem {
  val text: String?
  val includeDescription: Boolean?
  val sortBy: SortDto?
  val offset: Int?
  val count: Int?
}
