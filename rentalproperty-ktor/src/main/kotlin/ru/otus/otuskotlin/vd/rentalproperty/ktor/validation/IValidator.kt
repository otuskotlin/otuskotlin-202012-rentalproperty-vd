package ru.otus.otuskotlin.vd.rentalproperty.ktor.validation

interface IValidator<T> {
  infix fun validate(sample: T): ValidationResult
}
