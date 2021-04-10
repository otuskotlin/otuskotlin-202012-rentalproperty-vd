package ru.otus.otuskotlin.marketplace.business.logic.backend.operations

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.pipelines.kmp.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.pipelines.kmp.operation

object InitializePipeline : IOperation<BeContext> by operation({
  startIf { status == BeContextStatus.NONE }
  execute { status = BeContextStatus.RUNNING }
})