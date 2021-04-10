package ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.validation

import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.Pipeline

fun <C> Pipeline.Builder<C>.validation(block: ValidationBuilder<C>.() -> Unit) {
    execute(ValidationBuilder<C>().apply(block).build())
}
