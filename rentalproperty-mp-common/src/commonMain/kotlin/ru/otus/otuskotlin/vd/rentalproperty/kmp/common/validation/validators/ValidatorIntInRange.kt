package ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators

import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.IValidator
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.ValidationFieldError
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.ValidationResult

class ValidatorIntInRange<T : Comparable<T>>(
  private val field: String,
  private val min: T? = null,
  private val max: T? = null,
) : IValidator<T?> {
  override fun validate(sample: T?): ValidationResult = if (sample == null) {
    ValidationResult(
      errors = listOf(
        ValidationFieldError(
          field = field,
          message = "Field $field must not be null"
        )
      )
    )
  } else if (min != null && max != null && sample !in min..max) {
    ValidationResult(
      errors = listOf(
        ValidationFieldError(
          field = field,
          message = "Value $sample for field $field exceeds range [$min, $max]"
        )
      )
    )
  } else if (min == null && max != null && sample > max) {
    ValidationResult(
      errors = listOf(
        ValidationFieldError(
          field = field,
          message = "Value $sample for field $field more than [$max]"
        )
      )
    )
  } else if (min != null && max == null && sample < min) {
    ValidationResult(
      errors = listOf(
        ValidationFieldError(
          field = field,
          message = "Value $sample for field $field less  than [$min]"
        )
      )
    )
  } else {
    ValidationResult.SUCCESS
  }
}
