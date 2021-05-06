package ru.otus.otuskotlin.vd.rentalproperty.be.repository.tests

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IFlatRepository
import java.util.*

abstract class FlatsRepoBaseTest : StringSpec() {

  abstract val testFlatId1: FlatIdModel
  abstract val testFlatId2: FlatIdModel
  abstract val testFlatId3: FlatIdModel
  abstract val testFlatId4: FlatIdModel
  abstract val testFlatId5: FlatIdModel
  abstract val expectedFlatId: FlatIdModel
  abstract val repoFlat: IFlatRepository

  init {
    "list" {
      val context = BeContext(
        flatFilter = FlatFilterModel(
          text = "test-list",
          offset = 1,
          count = 2,
        )
      )
      val response = repoFlat.list(context)
      response shouldBe context.responseFlats
      2 shouldBe context.pageCount
      2 shouldBe response.size
    }

    "read" {
      val context = BeContext(
        requestFlatId = testFlatId3
      )
      val model = repoFlat.read(context)
      model shouldBe context.responseFlat
      "flat3" shouldBe model.description
    }

    "create" {
      mockkStatic(UUID::class) {
        expectedFlatId
        every {
          UUID.randomUUID()
        } returns expectedFlatId.asUUID()

        runBlocking {
          val flat = FlatModel(
            number = "created-number-flat",
            description = "created-flat",
          )
          val context = BeContext(
            requestFlat = flat
          )
          val result = repoFlat.create(context)
          result shouldBe context.responseFlat
          "created-number-flat" shouldBe result.number
          "created-flat" shouldBe result.description
          val context2 = BeContext(requestFlatId = result.id)
          repoFlat.read(context2)
          "created-number-flat" shouldBe context2.responseFlat.number
          "created-flat" shouldBe context2.responseFlat.description
        }
      }
    }

    "update" {
      val flat = FlatModel(
        id = testFlatId1,
        number = "updated-number-flat1",
        description = "updated-flat1",
      )
      val context = BeContext(
        requestFlat = flat
      )
      val result = repoFlat.update(context)
      result shouldBe context.responseFlat
      "updated-number-flat1" shouldBe result.number
      "updated-flat1" shouldBe result.description
      val context2 = BeContext(requestFlatId = testFlatId1)
      repoFlat.read(context2)
      "updated-number-flat1" shouldBe context2.responseFlat.number
      "updated-flat1" shouldBe context2.responseFlat.description
    }

    "delete" {
      val context = BeContext(
        requestFlatId = testFlatId5
      )
      val model = repoFlat.delete(context)
      model shouldBe context.responseFlat
      "flat-to-delete" shouldBe model.description
    }
  }

  companion object {}
}
