package ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory

import kotlinx.coroutines.runBlocking
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.realty.HouseRepoInMemory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

internal class HouseInMemoryTest {

  @OptIn(ExperimentalTime::class)
  @Test
  fun createAndGetTest() {
    val repo = HouseRepoInMemory(
      ttl = 5.toDuration(DurationUnit.MINUTES)
    )

    val house = HouseModel.STUB_SINGLE_HOUSE
    val context = BeContext(
      requestHouse = house
    )

    runBlocking {
      val createdResult = repo.create(context)
      assertEquals(house.address, createdResult.address)
      assertEquals(house.area, createdResult.area)
      assertEquals(house.yearConstruction, createdResult.yearConstruction)

      context.requestHouseId = createdResult.id
      val readResult = repo.read(context)
      assertEquals(createdResult.id, readResult.id)
      assertEquals(house.address, readResult.address)
      assertEquals(house.area, readResult.area)
      assertEquals(house.yearConstruction, readResult.yearConstruction)
    }
  }
}
