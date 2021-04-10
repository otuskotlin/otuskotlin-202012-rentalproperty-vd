package ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines

interface IOperation<T> {
  suspend fun execute(context: T)
}