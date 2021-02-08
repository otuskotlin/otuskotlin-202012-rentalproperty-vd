package ru.otus.otuskotlin.vd.rentalproperty.spring.validation.validators

import ru.otus.otuskotlin.vd.rentalproperty.spring.validation.IValidator
import ru.otus.otuskotlin.vd.rentalproperty.spring.validation.ValidationFieldError
import ru.otus.otuskotlin.vd.rentalproperty.spring.validation.ValidationResult

class ValidatorIntInRange<T : Comparable<T>>(
  private val field: String,
  private val min: T,
  private val max: T
) : IValidator<T> {
  override fun validate(sample: T): ValidationResult = if (sample in min..max) {
    ValidationResult.SUCCESS
  } else {
    ValidationResult(
      errors = listOf(
        ValidationFieldError(
          message = "Value $sample for field $field exceeds range [$min, $max]",
          field = field
        )
      )
    )
  }
}
