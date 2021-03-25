package ru.otus.otuskotlin.vd.rentalproperty.common.kmp.validation

data class ValidationDefaultError(
  override val message: String,
) : IValidationError
