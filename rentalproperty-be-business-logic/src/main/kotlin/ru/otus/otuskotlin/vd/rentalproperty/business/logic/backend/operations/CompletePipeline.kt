package ru.otus.otuskotlin.marketplace.business.logic.backend.operations

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.pipelines.kmp.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.pipelines.kmp.operation
import ru.otus.otuskotlin.vd.rentalproperty.pipelines.kmp.pipeline

object CompletePipeline : IOperation<BeContext> by pipeline({
  operation {
    startIf { status in setOf(BeContextStatus.RUNNING, BeContextStatus.FINISHING) }
    execute { status = BeContextStatus.SUCCESS }
  }
  operation {
    startIf { status != BeContextStatus.SUCCESS }
    execute { status = BeContextStatus.ERROR }
  }
})