package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryItemModel
import runBlockingTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DirectoryCrudTest {
  @Test
  fun filter() {
    val givenCrud = DirectoryCrud()
    val givenContext = BeContext(
      stubCase = StubCase.DIRECTORY_LIST_SUCCESS,
      directoryFilter = DirectoryFilterModel(text = "test")
    )

    runBlockingTest { givenCrud.list(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    assertEquals(1, givenContext.responseDirectoryItems.size)
    with(givenContext.responseDirectoryItems[0]) {
      assertEquals(DirectoryItemModel.STUB.id, id)
      assertEquals(DirectoryItemModel.STUB.name, name)
    }
  }

  @Test
  fun create() {
    val givenCrud = DirectoryCrud()
    val givenContext = BeContext(
      stubCase = StubCase.DIRECTORY_CREATE_SUCCESS,
      requestDirectoryItem = DirectoryItemModel.STUB
    )

    runBlockingTest { givenCrud.create(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    with(givenContext.responseDirectoryItem) {
      assertEquals(DirectoryItemModel.STUB.id, id)
      assertEquals(DirectoryItemModel.STUB.name, name)
    }
  }

  @Test
  fun read() {
    val givenCrud = DirectoryCrud()
    val givenContext = BeContext(
      stubCase = StubCase.DIRECTORY_READ_SUCCESS,
      requestDirectoryItemId = DirectoryItemModel.STUB.id
    )

    runBlockingTest { givenCrud.read(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    with(givenContext.responseDirectoryItem) {
      assertEquals(DirectoryItemModel.STUB.id, id)
      assertEquals(DirectoryItemModel.STUB.name, name)
    }
  }

  @Test
  fun update() {
    val givenCrud = DirectoryCrud()
    val givenContext = BeContext(
      stubCase = StubCase.DIRECTORY_UPDATE_SUCCESS,
      requestDirectoryItemId = DirectoryItemModel.STUB.id
    )

    runBlockingTest { givenCrud.update(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    with(givenContext.responseDirectoryItem) {
      assertEquals(DirectoryItemModel.STUB.id, id)
      assertEquals(DirectoryItemModel.STUB.name, name)
    }
  }

  @Test
  fun delete() {
    val givenCrud = DirectoryCrud()
    val givenContext = BeContext(
      stubCase = StubCase.DIRECTORY_DELETE_SUCCESS,
      requestDirectoryItemId = DirectoryItemModel.STUB.id
    )

    runBlockingTest { givenCrud.delete(givenContext) }

    assertEquals(BeContextStatus.SUCCESS, givenContext.status)
    with(givenContext.responseDirectoryItem) {
      assertEquals(DirectoryItemModel.STUB.id, id)
      assertEquals(DirectoryItemModel.STUB.name, name)
    }
  }
}