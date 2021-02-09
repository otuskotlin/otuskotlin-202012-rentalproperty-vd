package ru.otus.otuskotlin.vd.rentalproperty.mp

import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

internal class SharedClassTestJvm {
    @Test
    fun sharedClass() = runBlocking<Unit> {
        val sc = SharedClass()
        val res = sc.request("one")
        assertEquals("Jvm done", res)
    }
}
