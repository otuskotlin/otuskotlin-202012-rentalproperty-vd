package ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation

class ValidationResult(val errors: List<IValidationError>) {
  val isSuccess: Boolean
    get() = errors.isEmpty()

  companion object {
    val SUCCESS = ValidationResult(emptyList())
  }
}
