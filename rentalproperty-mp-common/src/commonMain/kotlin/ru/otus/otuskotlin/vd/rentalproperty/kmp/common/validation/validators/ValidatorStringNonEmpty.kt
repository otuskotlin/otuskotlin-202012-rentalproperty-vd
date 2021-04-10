package ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators

import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.IValidator
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.ValidationFieldError
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.ValidationResult


class ValidatorStringNonEmpty(
  private val field: String = "",
  private val message: String = "String must not be empty"
) : IValidator<String?> {

  override fun validate(sample: String?): ValidationResult {
    return if (sample.isNullOrBlank()) {
      ValidationResult(
        errors = listOf(
          ValidationFieldError(
            field = field,
            message = message,
          )
        )
      )
    } else {
      ValidationResult.SUCCESS
    }
  }

}
