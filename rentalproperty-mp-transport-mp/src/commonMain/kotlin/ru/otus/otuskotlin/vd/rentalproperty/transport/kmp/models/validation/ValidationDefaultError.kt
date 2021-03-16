package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.validation

data class ValidationDefaultError(
  override val message: String,
) : IValidationError
