package ru.otus.otuskotlin.vd.rentalproperty.common.kmp.validation

interface IValidator<T> {
  infix fun validate(sample: T): ValidationResult
}
