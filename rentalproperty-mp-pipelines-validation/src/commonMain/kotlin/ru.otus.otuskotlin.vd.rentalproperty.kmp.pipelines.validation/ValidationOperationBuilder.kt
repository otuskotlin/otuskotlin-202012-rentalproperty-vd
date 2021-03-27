package ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.validation

import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.IValidator
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.ValidationResult
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.PipelineDsl

@PipelineDsl
class ValidationOperationBuilder<C, T>(
    private var errorHandler: C.(ValidationResult) -> Unit = {}
) {
    private lateinit var onBlock: C.() -> T
    private lateinit var validator: IValidator<T>
    fun validator(validator: IValidator<T>) {
        this.validator = validator
    }

    fun on(block: C.() -> T) {
        onBlock = block
    }

    fun build(): IValidationOperation<C, T> {
        return DefaultValidationOperation(
            validator = validator,
            onBlock = onBlock,
            errorHandler = errorHandler
        )
    }
}
