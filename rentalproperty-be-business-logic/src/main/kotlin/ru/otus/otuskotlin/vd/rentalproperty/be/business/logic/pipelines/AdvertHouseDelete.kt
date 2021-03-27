package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.CompletePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.InitializePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.AdvertHouseDeleteStub
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object AdvertHouseDelete : IOperation<BeContext> by pipeline({
  execute(InitializePipeline)

  execute(AdvertHouseDeleteStub)

  execute(CompletePipeline)
})