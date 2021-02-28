package ru.otus.otuskotlin.vd.rentalproperty.mp

import kotlin.test.Test
import kotlin.test.assertEquals

internal class SharedModelTestJvm {
  @Test
  fun shmTest() {
    assertEquals("my name", SharedModel().`my name`)
  }
}
