package ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators

import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.IValidator
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.ValidationDefaultError
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.ValidationResult


class ValidatorStringNonEmpty : IValidator<String?> {

  override fun validate(sample: String?): ValidationResult {
    return if (sample.isNullOrBlank()) {
      ValidationResult(
        errors = listOf(
          ValidationDefaultError(
            message = "String must not be empty",
          )
        )
      )
    } else {
      ValidationResult.SUCCESS
    }
  }

}
