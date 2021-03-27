package ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines

interface IOperationBuilder<T> {
    fun build(): IOperation<T>
}
