package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseMaterialModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.operation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object HouseFilterStub : IOperation<BeContext> by pipeline({
  startIf { stubCase != StubCase.NONE }

  operation {
    startIf { stubCase == StubCase.HOUSE_LIST_SUCCESS }
    execute {
      responseHouses.add(
        HouseModel(
          id = HouseIdModel("test-id"),
          area = 160.4,
          address = "test-address",
          material = HouseMaterialModel(DirectoryIdModel("id"), "BRICK"),
          type = HouseTypeModel(DirectoryIdModel("id"), "SINGLE_HOUSE"),
          floors = 2,
          areaPlot = 15.0,
        )
      )
      status = BeContextStatus.FINISHING
    }
  }
})