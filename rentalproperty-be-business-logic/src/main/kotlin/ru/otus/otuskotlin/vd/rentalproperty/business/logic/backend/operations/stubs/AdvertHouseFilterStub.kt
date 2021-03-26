package ru.otus.otuskotlin.vd.rentalproperty.business.logic.backend.operations.stubs

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertHouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserIdModel
import ru.otus.otuskotlin.vd.rentalproperty.pipelines.kmp.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.pipelines.kmp.operation
import ru.otus.otuskotlin.vd.rentalproperty.pipelines.kmp.pipeline

object AdvertHouseFilterStub : IOperation<BeContext> by pipeline({
  startIf { stubCase != StubCase.NONE }

  operation {
    startIf { stubCase == StubCase.ADVERT_LIST_SUCCESS }
    execute {
      responseAdvertHouses.add(
        AdvertHouseModel(
          id = AdvertIdModel("test-id"),
          userId = UserIdModel("test-user-id"),
          name = "Продаётся дом",
          description = "Хороший дом",
          price = 1_500_000.0,
        )
      )
      status = BeContextStatus.FINISHING
    }
  }
})