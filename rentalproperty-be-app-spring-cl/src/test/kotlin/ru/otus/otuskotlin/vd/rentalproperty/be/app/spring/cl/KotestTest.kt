package ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.cl

import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

@DisplayName("Kotest")
class KotestTest : StringSpec({
  "Doing our first kotest" {
    "25".toInt() shouldBe 25
  }

  "Parameterized tests" {
    forAll<String, Int>(
      row("one", 3),
      row("two", 3),
      row("three", 5),
    ) { str: String, len: Int ->
      str.length shouldBe len
    }
  }
})
