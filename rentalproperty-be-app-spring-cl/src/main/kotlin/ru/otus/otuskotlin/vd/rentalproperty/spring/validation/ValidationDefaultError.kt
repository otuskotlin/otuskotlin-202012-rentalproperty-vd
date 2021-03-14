package ru.otus.otuskotlin.vd.rentalproperty.spring.validation

data class ValidationDefaultError(
  override val message: String,
) : IValidationError
