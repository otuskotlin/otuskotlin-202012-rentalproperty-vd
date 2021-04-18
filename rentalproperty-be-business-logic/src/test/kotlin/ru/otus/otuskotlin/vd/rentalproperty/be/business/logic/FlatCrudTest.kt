package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import runBlockingTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FlatCrudTest {
  @Test
  fun filter() {
    val givenCrud = FlatCrud()
    val givenContext = BeContext(
      stubCase = StubCase.FLAT_LIST_SUCCESS,
      flatFilter = FlatFilterModel(text = "test")
    )

    runBlockingTest { givenCrud.list(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    assertEquals(1, givenContext.responseFlats.size)
    with(givenContext.responseFlats[0]) {
      assertEquals(FlatModel.STUB.id, id)
      assertEquals(FlatModel.STUB.area, area)
      assertEquals(FlatModel.STUB.rooms, rooms)
      assertEquals(FlatModel.STUB.viewFromWindow, viewFromWindow)
    }
  }

  @Test
  fun create() {
    val givenCrud = FlatCrud()
    val givenContext = BeContext(
      stubCase = StubCase.FLAT_CREATE_SUCCESS,
      requestFlat = FlatModel.STUB
    )

    runBlockingTest { givenCrud.create(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    with(givenContext.responseFlat) {
      assertEquals(FlatModel.STUB.id, id)
      assertEquals(FlatModel.STUB.area, area)
      assertEquals(FlatModel.STUB.rooms, rooms)
      assertEquals(FlatModel.STUB.viewFromWindow, viewFromWindow)
    }
  }

  @Test
  fun read() {
    val givenCrud = FlatCrud()
    val givenContext = BeContext(
      stubCase = StubCase.FLAT_READ_SUCCESS,
      requestFlatId = FlatModel.STUB.id
    )

    runBlockingTest { givenCrud.read(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    with(givenContext.responseFlat) {
      assertEquals(FlatModel.STUB.id, id)
      assertEquals(FlatModel.STUB.area, area)
      assertEquals(FlatModel.STUB.rooms, rooms)
      assertEquals(FlatModel.STUB.viewFromWindow, viewFromWindow)
    }
  }

  @Test
  fun update() {
    val givenCrud = FlatCrud()
    val givenContext = BeContext(
      stubCase = StubCase.FLAT_UPDATE_SUCCESS,
      requestFlatId = FlatModel.STUB.id
    )

    runBlockingTest { givenCrud.update(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    with(givenContext.responseFlat) {
      assertEquals(FlatModel.STUB.id, id)
      assertEquals(FlatModel.STUB.area, area)
      assertEquals(FlatModel.STUB.rooms, rooms)
      assertEquals(FlatModel.STUB.viewFromWindow, viewFromWindow)
    }
  }

  @Test
  fun delete() {
    val givenCrud = FlatCrud()
    val givenContext = BeContext(
      stubCase = StubCase.FLAT_DELETE_SUCCESS,
      requestFlatId = FlatModel.STUB.id
    )

    runBlockingTest { givenCrud.delete(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    with(givenContext.responseFlat) {
      assertEquals(FlatModel.STUB.id, id)
      assertEquals(FlatModel.STUB.area, area)
      assertEquals(FlatModel.STUB.rooms, rooms)
      assertEquals(FlatModel.STUB.viewFromWindow, viewFromWindow)
    }
  }
}