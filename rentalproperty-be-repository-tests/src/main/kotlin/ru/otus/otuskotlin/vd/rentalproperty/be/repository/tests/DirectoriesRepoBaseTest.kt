package ru.otus.otuskotlin.vd.rentalproperty.be.repository.tests

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IDirectoryRepository
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.BathroomTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryItemIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.ViewFromWindowModel
import java.util.*

abstract class DirectoriesRepoBaseTest : StringSpec() {

  abstract val testDirectoryRepairTypeId1: DirectoryItemIdModel
  abstract val testDirectoryBathroomTypeId1: DirectoryItemIdModel
  abstract val testDirectoryConvenienceId3: DirectoryItemIdModel
  abstract val expectedDirectoryItemId: DirectoryItemIdModel
  abstract val repoDirectory: IDirectoryRepository

  init {
    "list" {
      val context = BeContext(
        directoryFilter = DirectoryFilterModel(
          text = "INTERNET",
          offset = 1,
          count = 10,
        )
      )
      val response = repoDirectory.list(context)
      response shouldBe context.responseDirectoryItems
      1 shouldBe context.pageCount
      1 shouldBe response.size
    }

    "read" {
      val context = BeContext(
        requestDirectoryItemId = testDirectoryRepairTypeId1
      )
      val model = repoDirectory.read(context)
      model shouldBe context.responseDirectoryItem
      "RENOVATION" shouldBe model.name
    }

    "create" {
      mockkStatic(UUID::class) {
        expectedDirectoryItemId
        every {
          UUID.randomUUID()
        } returns expectedDirectoryItemId.asUUID()

        runBlocking {
          val flat = ViewFromWindowModel(
            name = "FOREST",
          )
          val context = BeContext(
            requestDirectoryItem = flat
          )
          val result = repoDirectory.create(context)
          result shouldBe context.responseDirectoryItem
          "FOREST" shouldBe result.name
          val context2 = BeContext(requestDirectoryItemId = result.id)
          repoDirectory.read(context2)
          "FOREST" shouldBe context2.responseDirectoryItem.name
        }
      }
    }

    "update" {
      val item = BathroomTypeModel(
        id = testDirectoryBathroomTypeId1,
        name = "updated-COMBINED",
      )
      val context = BeContext(
        requestDirectoryItem = item
      )
      val result = repoDirectory.update(context)
      result shouldBe context.responseDirectoryItem
      "updated-COMBINED" shouldBe result.name
      val context2 = BeContext(requestDirectoryItemId = testDirectoryBathroomTypeId1)
      repoDirectory.read(context2)
      "updated-COMBINED" shouldBe context2.responseDirectoryItem.name
    }

    "delete" {
      val context = BeContext(
        requestDirectoryItemId = testDirectoryConvenienceId3
      )
      val model = repoDirectory.delete(context)
      model shouldBe context.responseDirectoryItem
      "PARKING" shouldBe model.name
    }
  }

  companion object {}
}
