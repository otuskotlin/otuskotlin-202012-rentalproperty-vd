package ru.otus.otuskotlin.vd.rentalproperty.be.repository.postgresql

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IFlatRepository
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.tests.FlatsRepoBaseTest

internal class SqlFlatsRepoTest : FlatsRepoBaseTest() {
  override val testFlatId1: FlatIdModel
    get() = SqlTestCompanion.testFlatId1

  override val testFlatId2: FlatIdModel
    get() = SqlTestCompanion.testFlatId2

  override val testFlatId3: FlatIdModel
    get() = SqlTestCompanion.testFlatId3

  override val testFlatId4: FlatIdModel
    get() = SqlTestCompanion.testFlatId4

  override val testFlatId5: FlatIdModel
    get() = SqlTestCompanion.testFlatId5

  override val expectedFlatId: FlatIdModel
    get() = SqlTestCompanion.expectedFlatId

  override val repoFlat: IFlatRepository
    get() = SqlTestCompanion.repoFlat
}
