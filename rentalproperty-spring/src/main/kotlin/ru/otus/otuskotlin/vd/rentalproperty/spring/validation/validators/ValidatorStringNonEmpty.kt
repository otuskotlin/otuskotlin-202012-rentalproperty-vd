package ru.otus.otuskotlin.vd.rentalproperty.spring.validation.validators

import ru.otus.otuskotlin.vd.rentalproperty.spring.validation.IValidator
import ru.otus.otuskotlin.vd.rentalproperty.spring.validation.ValidationDefaultError
import ru.otus.otuskotlin.vd.rentalproperty.spring.validation.ValidationResult

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
