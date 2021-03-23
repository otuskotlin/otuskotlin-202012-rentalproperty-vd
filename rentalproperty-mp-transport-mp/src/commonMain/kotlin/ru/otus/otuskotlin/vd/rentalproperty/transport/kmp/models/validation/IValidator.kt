package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.validation

interface IValidator<T> {
  infix fun validate(sample: T): ValidationResult
}
