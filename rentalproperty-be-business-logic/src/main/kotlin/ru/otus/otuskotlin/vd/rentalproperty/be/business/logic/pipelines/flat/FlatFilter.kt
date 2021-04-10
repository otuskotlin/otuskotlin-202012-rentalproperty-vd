package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.flat

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.CompletePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.InitializePipeline
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.flat.FlatFilterStub
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object FlatFilter : IOperation<BeContext> by pipeline({
  execute(InitializePipeline)

  execute(FlatFilterStub)

  execute(CompletePipeline)
})