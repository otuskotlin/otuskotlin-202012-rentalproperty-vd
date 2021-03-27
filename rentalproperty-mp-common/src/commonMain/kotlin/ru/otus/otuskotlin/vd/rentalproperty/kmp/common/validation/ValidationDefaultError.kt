package ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation

data class ValidationDefaultError(
  override val message: String,
) : IValidationError
