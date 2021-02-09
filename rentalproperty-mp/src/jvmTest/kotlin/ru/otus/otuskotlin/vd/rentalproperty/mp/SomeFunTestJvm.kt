package ru.otus.otuskotlin.vd.rentalproperty.mp

import kotlin.test.Test
import kotlin.test.assertTrue

class SomeFunTestJvm {
  @Test
  fun someFunTest() {
    val str = "someStr"
    assertTrue {
      someFun(str).contains("Jvm")
    }
  }
}
