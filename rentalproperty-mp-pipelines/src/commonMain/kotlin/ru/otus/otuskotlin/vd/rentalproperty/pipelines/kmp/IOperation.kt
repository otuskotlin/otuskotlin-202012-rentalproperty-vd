package ru.otus.otuskotlin.vd.rentalproperty.pipelines.kmp

interface IOperation<T> {
  suspend fun execute(context: T)
}