package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.validation

interface IValidationFieldError : IValidationError {
  val field: String
}
