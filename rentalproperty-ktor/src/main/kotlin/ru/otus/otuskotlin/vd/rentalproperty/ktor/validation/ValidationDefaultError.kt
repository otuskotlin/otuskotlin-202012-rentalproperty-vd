package ru.otus.otuskotlin.vd.rentalproperty.ktor.validation

data class ValidationDefaultError(
  override val message: String,
) : IValidationError
