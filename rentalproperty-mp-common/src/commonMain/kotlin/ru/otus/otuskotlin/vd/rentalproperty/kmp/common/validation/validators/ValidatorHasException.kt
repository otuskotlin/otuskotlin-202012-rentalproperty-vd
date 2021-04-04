package ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators

import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.IValidator
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.ValidationFieldError
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.ValidationResult
import kotlin.reflect.KClass

class ValidatorHasException(
  private val field: String = "",
  private val message: String = "List has an exception",
  private val klass: KClass<*> = Any::class
) : IValidator<List<Throwable>> {

  override fun validate(sample: List<Throwable>): ValidationResult =
    if (sample.firstOrNull { it::class == klass } != null) {
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
