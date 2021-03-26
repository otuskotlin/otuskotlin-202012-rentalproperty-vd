package ru.otus.otuskotlin.vd.rentalproperty.common.kmp.validation

data class ValidationFieldError(
  override val message: String,
  override val field: String,
) : IValidationError, IValidationFieldError
