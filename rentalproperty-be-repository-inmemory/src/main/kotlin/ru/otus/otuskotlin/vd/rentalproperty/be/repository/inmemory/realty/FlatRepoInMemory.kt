package ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.realty

import org.cache2k.Cache
import org.cache2k.Cache2kBuilder
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoIndexException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoNotFoundException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoWrongIdException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IFlatRepository
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class FlatRepoInMemory @OptIn(ExperimentalTime::class) constructor(
  ttl: Duration,
  initObjects: Collection<FlatModel> = emptyList()
) : IFlatRepository {
  @OptIn(ExperimentalTime::class)
  private var cache: Cache<String, FlatInMemoryDto> = object : Cache2kBuilder<String, FlatInMemoryDto>() {}
    .expireAfterWrite(ttl.toLongMilliseconds(), TimeUnit.MILLISECONDS) // expire/refresh after 5 minutes
    .suppressExceptions(false)
    .build()
    .also { cache ->
      initObjects.forEach {
        cache.put(it.id.id, FlatInMemoryDto.of(it))
      }
    }

  override suspend fun read(context: BeContext): FlatModel {
    val id = context.requestFlatId
    if (id == FlatIdModel.NONE) throw RepoWrongIdException(id.id)
    val model = cache.get(id.id)?.toModel() ?: throw RepoNotFoundException(id.id)
    context.responseFlat = model
    return model
  }

  override suspend fun create(context: BeContext): FlatModel {
    val dto = FlatInMemoryDto.of(context.requestFlat, UUID.randomUUID().toString())
    val model = save(dto).toModel()
    context.responseFlat = model
    return model
  }

  override suspend fun update(context: BeContext): FlatModel {
    if (context.requestFlat.id == FlatIdModel.NONE) throw RepoWrongIdException(context.requestFlat.id.id)
    val model = save(FlatInMemoryDto.of(context.requestFlat)).toModel()
    context.responseFlat = model
    return model
  }

  override suspend fun delete(context: BeContext): FlatModel {
    val id = context.requestFlatId
    if (id == FlatIdModel.NONE) throw RepoWrongIdException(id.id)
    val model = cache.peekAndRemove(id.id)?.toModel() ?: throw RepoNotFoundException(id.id)
    context.responseFlat = model
    return model
  }

  override suspend fun list(context: BeContext): Collection<FlatModel> {
    val textFilter = context.flatFilter.text
    if (textFilter.length < 3) throw RepoIndexException(textFilter)
    val records = cache.asMap().filterValues {
      it.number?.contains(textFilter, true) ?: false
          || if (context.flatFilter.includeDescription) {
        it.description?.contains(textFilter, true) ?: false
      } else false
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
    context.responseFlats = list.toMutableList()
    context.pageCount = list.count().takeIf { it != 0 }
      ?.let { (records.count().toDouble() / it + 0.5).toInt() }
      ?: Int.MIN_VALUE
    return list
  }

  private suspend fun save(dto: FlatInMemoryDto): FlatInMemoryDto {
    cache.put(dto.id, dto)
    return cache.get(dto.id)
  }
}
