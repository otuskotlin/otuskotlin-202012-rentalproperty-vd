package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.house

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.operation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object HouseUpdateStub : IOperation<BeContext> by pipeline({
  startIf { stubCase != StubCase.NONE }

  operation {
    startIf { stubCase == StubCase.HOUSE_UPDATE_SUCCESS }
    execute {
      responseHouse = HouseModel.STUB_MULTI_APARTMENT
      status = BeContextStatus.FINISHING
    }
  }
})