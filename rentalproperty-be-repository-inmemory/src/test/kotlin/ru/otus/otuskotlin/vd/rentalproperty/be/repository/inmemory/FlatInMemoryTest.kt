package ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory

import kotlinx.coroutines.runBlocking
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.realty.FlatRepoInMemory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

internal class FlatInMemoryTest {

  @OptIn(ExperimentalTime::class)
  @Test
  fun createAndGetTest() {
    val repo = FlatRepoInMemory(
      ttl = 5.toDuration(DurationUnit.MINUTES)
    )

    val flat = FlatModel.STUB
    val context = BeContext(
      requestFlat = flat
    )

    runBlocking {
      val createdResult = repo.create(context)
      assertEquals(flat.number, createdResult.number)
      assertEquals(flat.area, createdResult.area)
      assertEquals(flat.description, createdResult.description)

      context.requestFlatId = createdResult.id
      val readResult = repo.read(context)
      assertEquals(createdResult.id, readResult.id)
      assertEquals(flat.number, readResult.number)
      assertEquals(flat.area, readResult.area)
      assertEquals(flat.description, readResult.description)
    }
  }
}
