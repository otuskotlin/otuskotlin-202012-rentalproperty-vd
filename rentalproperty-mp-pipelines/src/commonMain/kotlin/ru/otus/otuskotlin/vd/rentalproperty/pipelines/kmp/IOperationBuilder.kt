package ru.otus.otuskotlin.vd.rentalproperty.pipelines.kmp

interface IOperationBuilder<T> {
    fun build(): IOperation<T>
}
