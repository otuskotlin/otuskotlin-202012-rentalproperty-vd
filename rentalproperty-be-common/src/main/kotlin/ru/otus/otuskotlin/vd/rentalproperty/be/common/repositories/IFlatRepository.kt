package ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel

interface IFlatRepository {
  suspend fun read(context: BeContext): FlatModel
  suspend fun create(context: BeContext): FlatModel
  suspend fun update(context: BeContext): FlatModel
  suspend fun delete(context: BeContext): FlatModel

  suspend fun list(context: BeContext): Collection<FlatModel>

  companion object {
    val NONE = object : IFlatRepository {
      override suspend fun read(context: BeContext): FlatModel {
        TODO("Not yet implemented")
      }

      override suspend fun create(context: BeContext): FlatModel {
        TODO("Not yet implemented")
      }

      override suspend fun update(context: BeContext): FlatModel {
        TODO("Not yet implemented")
      }

      override suspend fun delete(context: BeContext): FlatModel {
        TODO("Not yet implemented")
      }

      override suspend fun list(context: BeContext): Collection<FlatModel> {
        TODO("Not yet implemented")
      }
    }
  }
}
