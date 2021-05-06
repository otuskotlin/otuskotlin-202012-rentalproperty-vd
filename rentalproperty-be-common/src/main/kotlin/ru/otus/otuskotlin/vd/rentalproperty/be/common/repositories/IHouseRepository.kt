package ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel

interface IHouseRepository {
  suspend fun read(context: BeContext): HouseModel
  suspend fun create(context: BeContext): HouseModel
  suspend fun update(context: BeContext): HouseModel
  suspend fun delete(context: BeContext): HouseModel
  suspend fun list(context: BeContext): Collection<HouseModel>

  companion object {
    val NONE = object : IHouseRepository {
      override suspend fun read(context: BeContext): HouseModel {
        TODO("Not yet implemented")
      }

      override suspend fun create(context: BeContext): HouseModel {
        TODO("Not yet implemented")
      }

      override suspend fun update(context: BeContext): HouseModel {
        TODO("Not yet implemented")
      }

      override suspend fun delete(context: BeContext): HouseModel {
        TODO("Not yet implemented")
      }

      override suspend fun list(context: BeContext): Collection<HouseModel> {
        TODO("Not yet implemented")
      }
    }
  }
}
