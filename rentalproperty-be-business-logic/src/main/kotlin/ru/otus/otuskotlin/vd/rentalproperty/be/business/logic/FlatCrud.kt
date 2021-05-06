package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic

import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.pipelines.flat.*
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IFlatRepository

class FlatCrud(
  private val flatRepoTest: IFlatRepository = IFlatRepository.NONE,
  private val flatRepoProd: IFlatRepository = IFlatRepository.NONE,
) {
  suspend fun list(context: BeContext) {
    FlatFilter.execute(context.apply(this::configureContext))
  }

  suspend fun create(context: BeContext) {
    FlatCreate.execute(context.apply(this::configureContext))
  }

  suspend fun read(context: BeContext) {
    FlatRead.execute(context.apply(this::configureContext))
  }

  suspend fun update(context: BeContext) {
    FlatUpdate.execute(context.apply(this::configureContext))
  }

  suspend fun delete(context: BeContext) {
    FlatDelete.execute(context.apply(this::configureContext))
  }

  private fun configureContext(context: BeContext) {
    context.flatRepoTest = flatRepoTest
    context.flatRepoProd = flatRepoProd
  }
}