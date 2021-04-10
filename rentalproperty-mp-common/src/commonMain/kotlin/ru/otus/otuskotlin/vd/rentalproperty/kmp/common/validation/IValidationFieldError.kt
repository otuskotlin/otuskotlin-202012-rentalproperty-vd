package ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation

interface IValidationFieldError : IValidationError {
  val field: String
}
