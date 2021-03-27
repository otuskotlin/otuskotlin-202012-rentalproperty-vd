package ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.validation

import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.IValidationError
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.ValidationResult
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.validation.validators.ValidatorStringNonEmpty
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline
import runBlockingTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ValidationTest {

    @Test
    fun pipelineValidation() {
        val pl = pipeline<TestContext> {

            validation {
                errorHandler { v: ValidationResult ->
                    if (v.isSuccess) return@errorHandler
                    errors.addAll(v.errors)
                }

                validate<String?> { validator(ValidatorStringNonEmpty()); on { x } }
                validate<String?> { validator(ValidatorStringNonEmpty()); on { y } }
            }
        }
        runBlockingTest {
            val ctx = TestContext()
            pl.execute(ctx)
            assertEquals(2, ctx.errors.size)
        }
    }

    data class TestContext(
        val x: String = "",
        val y: String = "",
        val errors: MutableList<IValidationError> = mutableListOf()
    )
}

