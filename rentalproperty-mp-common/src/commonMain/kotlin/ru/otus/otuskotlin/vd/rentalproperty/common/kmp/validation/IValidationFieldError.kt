package ru.otus.otuskotlin.vd.rentalproperty.common.kmp.validation

interface IValidationFieldError : IValidationError {
  val field: String
}
