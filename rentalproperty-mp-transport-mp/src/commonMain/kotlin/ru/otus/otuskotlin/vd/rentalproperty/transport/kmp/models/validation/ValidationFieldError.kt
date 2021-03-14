package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.validation

data class ValidationFieldError(
  override val message: String,
  override val field: String,
) : IValidationError, IValidationFieldError
