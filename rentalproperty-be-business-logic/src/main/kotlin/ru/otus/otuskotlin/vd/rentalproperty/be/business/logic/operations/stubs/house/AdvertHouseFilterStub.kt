package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.house

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertHouseModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.operation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object AdvertHouseFilterStub : IOperation<BeContext> by pipeline({
  startIf {
    status == BeContextStatus.RUNNING
        && stubCase != StubCase.NONE
  }

  operation {
    startIf { stubCase == StubCase.ADVERT_LIST_SUCCESS }
    execute {
      responseAdvertHouses.add(AdvertHouseModel.STUB)
      status = BeContextStatus.FINISHING
    }
  }
})