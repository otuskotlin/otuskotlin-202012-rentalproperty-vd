package ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators

import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.IValidator
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.ValidationFieldError
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.ValidationResult

class ValidatorHasInList(
  private val field: String = "",
  private val message: String = "List does not contain target value",
  private val values: List<String> = emptyList(),
) : IValidator<List<String>> {

  override fun validate(sample: List<String>): ValidationResult =
    if (sample.containsAll(values)) {
      ValidationResult.SUCCESS
    } else {
      ValidationResult(
        errors = listOf(
          ValidationFieldError(
            field = field,
            message = message,
          )
        )
      )
    }
}
