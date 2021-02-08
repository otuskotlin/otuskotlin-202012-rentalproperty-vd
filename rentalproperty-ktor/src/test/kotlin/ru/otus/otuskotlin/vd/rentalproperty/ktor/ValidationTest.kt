package ru.otus.otuskotlin.vd.rentalproperty.ktor

import kotlinx.coroutines.runBlocking
import ru.otus.otuskotlin.vd.rentalproperty.ktor.validation.validators.ValidatorIntInRange
import ru.otus.otuskotlin.vd.rentalproperty.ktor.validation.validators.ValidatorStringNonEmpty
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class ValidationTest {
    @Test
    fun createValidationTest() = runBlocking {
        val validator = ValidatorStringNonEmpty()
        val res = validator validate ""
        assertEquals(false, res.isSuccess)
        assertTrue {
            res.errors.first().message.contains("empty")
        }
    }

    @Test
    fun infixValidationTest() = runBlocking {
        val validator = ValidatorIntInRange("age", 2, 5)
        val res = validator validate 8
        assertEquals(false, res.isSuccess)
        assertTrue {
            res.errors.first().message.contains("range")
        }
    }
}

