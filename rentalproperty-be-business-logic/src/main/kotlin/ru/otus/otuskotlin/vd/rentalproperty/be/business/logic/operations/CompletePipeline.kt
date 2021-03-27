package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.operation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

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