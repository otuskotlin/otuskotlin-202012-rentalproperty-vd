package ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.IDirectoryItemModel

interface IDirectoryRepository {
  suspend fun read(context: BeContext): IDirectoryItemModel
  suspend fun create(context: BeContext): IDirectoryItemModel
  suspend fun update(context: BeContext): IDirectoryItemModel
  suspend fun delete(context: BeContext): IDirectoryItemModel

  suspend fun list(context: BeContext): Collection<IDirectoryItemModel>

  companion object {
    val NONE = object : IDirectoryRepository {
      override suspend fun read(context: BeContext): IDirectoryItemModel {
        TODO("Not yet implemented")
      }

      override suspend fun create(context: BeContext): IDirectoryItemModel {
        TODO("Not yet implemented")
      }

      override suspend fun update(context: BeContext): IDirectoryItemModel {
        TODO("Not yet implemented")
      }

      override suspend fun delete(context: BeContext): IDirectoryItemModel {
        TODO("Not yet implemented")
      }

      override suspend fun list(context: BeContext): Collection<IDirectoryItemModel> {
        TODO("Not yet implemented")
      }
    }
  }
}
