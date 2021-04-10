package ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation

interface IValidator<T> {
  infix fun validate(sample: T): ValidationResult
}
