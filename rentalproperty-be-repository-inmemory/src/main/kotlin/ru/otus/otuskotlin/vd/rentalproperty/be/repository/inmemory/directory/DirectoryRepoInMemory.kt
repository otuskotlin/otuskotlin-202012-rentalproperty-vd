package ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.directory

import org.cache2k.Cache
import org.cache2k.Cache2kBuilder
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoIndexException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoNotFoundException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoWrongIdException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IDirectoryRepository
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryItemIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.IDirectoryItemModel
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class DirectoryRepoInMemory @OptIn(ExperimentalTime::class) constructor(
  ttl: Duration,
  initObjects: Collection<IDirectoryItemModel> = emptyList()
) : IDirectoryRepository {
  @OptIn(ExperimentalTime::class)
  private var cache: Cache<String, DirectoryInMemoryDto> = object : Cache2kBuilder<String, DirectoryInMemoryDto>() {}
    .expireAfterWrite(ttl.toLongMilliseconds(), TimeUnit.MILLISECONDS) // expire/refresh after 5 minutes
    .suppressExceptions(false)
    .build()
    .also { cache ->
      initObjects.forEach {
        cache.put(it.id.id, DirectoryInMemoryDto.of(it))
      }
    }

  override suspend fun read(context: BeContext): IDirectoryItemModel {
    val id = context.requestDirectoryItemId
    if (id == DirectoryItemIdModel.NONE) throw RepoWrongIdException(id.id)
    val model = cache.get(id.id)?.toModel() ?: throw RepoNotFoundException(id.id)
    context.responseDirectoryItem = model
    return model
  }

  override suspend fun create(context: BeContext): IDirectoryItemModel {
    val dto = DirectoryInMemoryDto.of(context.requestDirectoryItem, UUID.randomUUID().toString())
    val model = save(dto).toModel()
    context.responseDirectoryItem = model
    return model
  }

  override suspend fun update(context: BeContext): IDirectoryItemModel {
    if (context.requestDirectoryItem.id == DirectoryItemIdModel.NONE) throw RepoWrongIdException(context.requestDirectoryItem.id.id)
    val model = save(DirectoryInMemoryDto.of(context.requestDirectoryItem)).toModel()
    context.responseDirectoryItem = model
    return model
  }

  override suspend fun delete(context: BeContext): IDirectoryItemModel {
    val id = context.requestDirectoryItemId
    if (id == DirectoryItemIdModel.NONE) throw RepoWrongIdException(id.id)
    val model = cache.peekAndRemove(id.id)?.toModel() ?: throw RepoNotFoundException(id.id)
    context.responseDirectoryItem = model
    return model
  }

  override suspend fun list(context: BeContext): Collection<IDirectoryItemModel> {
    val textFilter = context.flatFilter.text
    if (textFilter.length < 3) throw RepoIndexException(textFilter)
    val records = cache.asMap().filterValues {
      it.name?.contains(textFilter, true) ?: false
    }.values
    if (records.count() <= context.flatFilter.offset)
      throw RepoIndexException(textFilter)
    val list = records.toList()
      .subList(
        context.flatFilter.offset,
        if (records.count() >= context.flatFilter.offset + context.flatFilter.count)
          context.flatFilter.offset + context.flatFilter.count
        else records.count()
      ).map { it.toModel() }
    context.responseDirectoryItems = list.toMutableList()
    context.pageCount = list.count().takeIf { it != 0 }
      ?.let { (records.count().toDouble() / it + 0.5).toInt() }
      ?: Int.MIN_VALUE
    return list
  }

  private suspend fun save(dto: DirectoryInMemoryDto): DirectoryInMemoryDto {
    cache.put(dto.id, dto)
    return cache.get(dto.id)
  }
}
