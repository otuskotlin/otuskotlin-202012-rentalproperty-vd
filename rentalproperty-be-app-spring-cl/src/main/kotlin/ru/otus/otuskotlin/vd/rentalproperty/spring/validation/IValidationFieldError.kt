package ru.otus.otuskotlin.vd.rentalproperty.spring.validation

interface IValidationFieldError : IValidationError {
  val field: String
}
