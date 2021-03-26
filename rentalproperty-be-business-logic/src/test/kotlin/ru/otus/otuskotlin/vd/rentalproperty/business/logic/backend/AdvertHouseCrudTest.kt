package ru.otus.otuskotlin.vd.rentalproperty.business.logic.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertHouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserIdModel
import runBlockingTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AdvertHouseCrudTest {
  @Test
  fun filter() {
    val givenCrud = AdvertHouseCrud()
    val givenContext = BeContext(
      stubCase = StubCase.ADVERT_LIST_SUCCESS,
      advertFilter = AdvertFilterModel(text = "test")
    )

    runBlockingTest { givenCrud.list(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    assertEquals(1, givenContext.responseAdvertHouses.size)
    with(givenContext.responseAdvertHouses[0]) {
      assertEquals(AdvertIdModel("test-id"), id)
      assertEquals(UserIdModel("test-user-id"), userId)
      assertEquals("Продаётся дом", name)
      assertEquals(1_500_000.0, price)
    }
  }

  @Test
  fun create() {
    val givenCrud = AdvertHouseCrud()
    val givenContext = BeContext(
      stubCase = StubCase.ADVERT_CREATE_SUCCESS,
      requestAdvertHouse = AdvertHouseModel(
        userId = UserIdModel("test-user-id"),
        name = "Продаётся дом",
        description = "Хороший дом",
        price = 1_500_000.0,
      )
    )

    runBlockingTest { givenCrud.create(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    with(givenContext.responseAdvertHouse) {
      assertEquals(AdvertIdModel("test-id"), id)
      assertEquals(UserIdModel("test-user-id"), userId)
      assertEquals("Продаётся дом", name)
      assertEquals(1_500_000.0, price)
    }
  }

  @Test
  fun read() {
    val givenCrud = AdvertHouseCrud()
    val givenContext = BeContext(
      stubCase = StubCase.ADVERT_READ_SUCCESS,
      requestAdvertHouseId = AdvertIdModel("test-id")
    )

    runBlockingTest { givenCrud.read(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    with(givenContext.responseAdvertHouse) {
      assertEquals(AdvertIdModel("test-id"), id)
      assertEquals(UserIdModel("test-user-id"), userId)
      assertEquals("Продаётся дом", name)
      assertEquals(1_500_000.0, price)
    }
  }

  @Test
  fun update() {
    val givenCrud = AdvertHouseCrud()
    val givenContext = BeContext(
      stubCase = StubCase.ADVERT_UPDATE_SUCCESS,
      requestAdvertHouse = AdvertHouseModel(
        id = AdvertIdModel("test-id"),
        userId = UserIdModel("test-user-id"),
        name = "Продаётся дом",
        description = "Хороший дом",
        price = 1_500_000.0,
      )
    )

    runBlockingTest { givenCrud.update(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    with(givenContext.responseAdvertHouse) {
      assertEquals(AdvertIdModel("test-id"), id)
      assertEquals(UserIdModel("test-user-id"), userId)
      assertEquals("Продаётся дом", name)
      assertEquals(1_500_000.0, price)
    }
  }

  @Test
  fun delete() {
    val givenCrud = AdvertHouseCrud()
    val givenContext = BeContext(
      stubCase = StubCase.ADVERT_DELETE_SUCCESS,
      requestAdvertHouseId = AdvertIdModel("test-id")
    )

    runBlockingTest { givenCrud.delete(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    with(givenContext.responseAdvertHouse) {
      assertEquals(AdvertIdModel("test-id"), id)
      assertEquals(UserIdModel("test-user-id"), userId)
      assertEquals("Продаётся дом", name)
      assertEquals(1_500_000.0, price)
    }
  }
}