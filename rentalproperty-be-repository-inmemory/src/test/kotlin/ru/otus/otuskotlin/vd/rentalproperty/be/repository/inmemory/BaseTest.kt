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

internal class BaseTest {

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
      val createdFlat = repo.create(context)
      assertEquals(flat.number, createdFlat.number)
      assertEquals(flat.area, createdFlat.area)
      assertEquals(flat.description, createdFlat.description)

      context.requestFlatId = createdFlat.id
      val readFlat = repo.read(context)
      assertEquals(createdFlat.id, readFlat.id)
      assertEquals(flat.number, createdFlat.number)
      assertEquals(flat.area, createdFlat.area)
      assertEquals(flat.description, createdFlat.description)
    }
  }
}
