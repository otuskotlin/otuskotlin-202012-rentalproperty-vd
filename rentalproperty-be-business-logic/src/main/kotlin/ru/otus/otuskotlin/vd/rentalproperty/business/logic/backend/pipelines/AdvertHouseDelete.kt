package ru.otus.otuskotlin.vd.rentalproperty.business.logic.backend.pipelines

import ru.otus.otuskotlin.marketplace.business.logic.backend.operations.CompletePipeline
import ru.otus.otuskotlin.marketplace.business.logic.backend.operations.InitializePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.business.logic.backend.operations.stubs.AdvertHouseDeleteStub
import ru.otus.otuskotlin.vd.rentalproperty.pipelines.kmp.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.pipelines.kmp.pipeline

object AdvertHouseDelete : IOperation<BeContext> by pipeline({
  execute(InitializePipeline)

  execute(AdvertHouseDeleteStub)

  execute(CompletePipeline)
})