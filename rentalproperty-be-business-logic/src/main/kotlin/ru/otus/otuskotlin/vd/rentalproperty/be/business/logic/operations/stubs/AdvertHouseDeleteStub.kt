package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertHouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserIdModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.operation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object AdvertHouseDeleteStub : IOperation<BeContext> by pipeline({
  startIf { stubCase != StubCase.NONE }

  operation {
    startIf { stubCase == StubCase.ADVERT_DELETE_SUCCESS }
    execute {
      responseAdvertHouse = AdvertHouseModel(
        id = AdvertIdModel("test-id"),
        userId = UserIdModel("test-user-id"),
        name = "Продаётся дом",
        description = "Хороший дом",
        price = 1_500_000.0,
      )
      status = BeContextStatus.FINISHING
    }
  }
})