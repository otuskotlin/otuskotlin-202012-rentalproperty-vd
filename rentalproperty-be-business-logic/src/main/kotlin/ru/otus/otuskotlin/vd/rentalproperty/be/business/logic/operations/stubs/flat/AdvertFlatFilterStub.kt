package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.flat

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertFlatModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.operation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object AdvertFlatFilterStub : IOperation<BeContext> by pipeline({
  startIf {
    status == BeContextStatus.RUNNING
        && stubCase != StubCase.NONE
  }

  operation {
    startIf { stubCase == StubCase.ADVERT_LIST_SUCCESS }
    execute {
      responseAdvertFlats.add(AdvertFlatModel.STUB)
      status = BeContextStatus.FINISHING
    }
  }
})