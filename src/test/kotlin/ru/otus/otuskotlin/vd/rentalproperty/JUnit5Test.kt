package ru.otus.otuskotlin.vd.rentalproperty

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class JUnit5Test {
    @ParameterizedTest
    @ValueSource(
        strings = [
            "one",
            "two",
            "three",
        ]
    )
    fun paramTest(param: String) {
        println(param)
    }
}
