package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.operation

object InitializePipeline : IOperation<BeContext> by operation({
  startIf { status == BeContextStatus.NONE }
  execute { status = BeContextStatus.RUNNING }
})