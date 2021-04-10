package ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation

data class ValidationFieldError(
  override val message: String,
  override val field: String,
) : IValidationError, IValidationFieldError
