package ru.otus.otuskotlin.vd.rentalproperty.be.repository.postgresql

import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IDirectoryRepository
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryItemIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.tests.DirectoriesRepoBaseTest

internal class SqlDirectoriesRepoTest : DirectoriesRepoBaseTest() {
  override val testDirectoryRepairTypeId1: DirectoryItemIdModel
    get() = SqlTestCompanion.testDirectoryRepairTypeId1

  override val testDirectoryBathroomTypeId1: DirectoryItemIdModel
    get() = SqlTestCompanion.testDirectoryBathroomTypeId1

  override val testDirectoryConvenienceId3: DirectoryItemIdModel
    get() = SqlTestCompanion.testDirectoryConvenienceId3

  override val expectedDirectoryItemId: DirectoryItemIdModel
    get() = SqlTestCompanion.expectedDirectoryItemId

  override val repoDirectory: IDirectoryRepository
    get() = SqlTestCompanion.repoDirectory
}
