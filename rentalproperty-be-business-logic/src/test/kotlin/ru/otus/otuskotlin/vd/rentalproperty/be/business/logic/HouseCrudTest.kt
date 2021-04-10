package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import runBlockingTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HouseCrudTest {
  @Test
  fun filter() {
    val givenCrud = HouseCrud()
    val givenContext = BeContext(
      stubCase = StubCase.HOUSE_LIST_SUCCESS,
      houseFilter = HouseFilterModel(text = "test")
    )

    runBlockingTest { givenCrud.list(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    assertEquals(1, givenContext.responseHouses.size)
    with(givenContext.responseHouses[0]) {
      assertEquals(HouseIdModel("test-house-id"), id)
      assertEquals("test-address", address)
      assertEquals(160.4, area)
      assertEquals("SINGLE_HOUSE", type.name)
      assertEquals(2, floors)
    }
  }

  @Test
  fun create() {
    val givenCrud = HouseCrud()
    val givenContext = BeContext(
      stubCase = StubCase.HOUSE_CREATE_SUCCESS,
      requestHouse = HouseModel.STUB_SINGLE_HOUSE
    )

    runBlockingTest { givenCrud.create(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    with(givenContext.responseHouse) {
      assertEquals(HouseIdModel("test-house-id"), id)
      assertEquals("test-address", address)
      assertEquals(160.4, area)
      assertEquals("SINGLE_HOUSE", type.name)
      assertEquals(2, floors)
    }
  }

  @Test
  fun read() {
    val givenCrud = HouseCrud()
    val givenContext = BeContext(
      stubCase = StubCase.HOUSE_READ_SUCCESS,
      requestHouseId = HouseIdModel("test-house-id")
    )

    runBlockingTest { givenCrud.read(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    with(givenContext.responseHouse) {
      assertEquals(HouseIdModel("test-house-id"), id)
      assertEquals("test-address", address)
      assertEquals(160.4, area)
      assertEquals("SINGLE_HOUSE", type.name)
      assertEquals(2, floors)
    }
  }

  @Test
  fun update() {
    val givenCrud = HouseCrud()
    val givenContext = BeContext(
      stubCase = StubCase.HOUSE_UPDATE_SUCCESS,
      requestHouse = HouseModel.STUB_SINGLE_HOUSE
    )

    runBlockingTest { givenCrud.update(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    with(givenContext.responseHouse) {
      assertEquals(HouseIdModel("test-house-id"), id)
      assertEquals("test-address", address)
      assertEquals(160.4, area)
      assertEquals("SINGLE_HOUSE", type.name)
      assertEquals(2, floors)
    }
  }

  @Test
  fun delete() {
    val givenCrud = HouseCrud()
    val givenContext = BeContext(
      stubCase = StubCase.HOUSE_DELETE_SUCCESS,
      requestHouseId = HouseIdModel("test-house-id")
    )

    runBlockingTest { givenCrud.delete(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    with(givenContext.responseHouse) {
      assertEquals(HouseIdModel("test-house-id"), id)
      assertEquals("test-address", address)
      assertEquals(160.4, area)
      assertEquals("SINGLE_HOUSE", type.name)
      assertEquals(2, floors)
    }
  }
}