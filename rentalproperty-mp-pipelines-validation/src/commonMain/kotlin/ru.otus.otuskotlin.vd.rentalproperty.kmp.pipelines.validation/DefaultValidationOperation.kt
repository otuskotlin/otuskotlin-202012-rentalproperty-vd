package ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.validation

import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.IValidator
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.ValidationResult

class DefaultValidationOperation<C, T>(
    private val onBlock: C.() -> T,
    private val validator: IValidator<T>,
    private var errorHandler: C.(ValidationResult) -> Unit = {}
) : IValidationOperation<C, T> {
    override suspend fun execute(context: C) {
        val value = context.onBlock()
        val res = validator.validate(value)
        context.errorHandler(res)
    }

}
