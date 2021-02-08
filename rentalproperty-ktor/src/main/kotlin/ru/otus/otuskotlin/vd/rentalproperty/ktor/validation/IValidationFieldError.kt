package ru.otus.otuskotlin.vd.rentalproperty.ktor.validation

interface IValidationFieldError : IValidationError {
  val field: String
}
