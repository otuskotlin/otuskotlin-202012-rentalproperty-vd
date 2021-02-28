package ru.otus.otuskotlin.vd.rentalproperty.spring.validation

interface IValidator<T> {
  infix fun validate(sample: T): ValidationResult
}
