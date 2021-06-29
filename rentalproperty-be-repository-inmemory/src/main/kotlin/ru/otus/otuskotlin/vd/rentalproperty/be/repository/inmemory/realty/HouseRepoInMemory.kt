package ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.realty

import org.cache2k.Cache
import org.cache2k.Cache2kBuilder
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoIndexException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoNotFoundException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.exceptions.RepoWrongIdException
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IHouseRepository
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

class HouseRepoInMemory @OptIn(ExperimentalTime::class) constructor(
  ttl: Duration = 30.toDuration(DurationUnit.SECONDS),
  initObjects: Collection<HouseModel> = emptyList()
) : IHouseRepository {
  @OptIn(ExperimentalTime::class)
  private var cache: Cache<String, HouseInMemoryDto> = object : Cache2kBuilder<String, HouseInMemoryDto>() {}
    .expireAfterWrite(ttl.toLongMilliseconds(), TimeUnit.MILLISECONDS) // expire/refresh after 5 minutes
    .suppressExceptions(false)
    .build()
    .also { cache ->
      initObjects.forEach {
        cache.put(it.id.id, HouseInMemoryDto.of(it))
      }
    }

  override suspend fun read(context: BeContext): HouseModel {
    val id = context.requestHouseId
    if (id == HouseIdModel.NONE) throw RepoWrongIdException(id.id)
    val model = cache.get(id.id)?.toModel() ?: throw RepoNotFoundException(id.id)
    context.responseHouse = model
    return model
  }

  override suspend fun create(context: BeContext): HouseModel {
    val dto = HouseInMemoryDto.of(context.requestHouse, UUID.randomUUID().toString())
    val model = save(dto).toModel()
    context.responseHouse = model
    return model
  }

  override suspend fun update(context: BeContext): HouseModel {
    if (context.requestHouse.id == HouseIdModel.NONE) throw RepoWrongIdException(context.requestHouse.id.id)
    val model = save(HouseInMemoryDto.of(context.requestHouse)).toModel()
    context.responseHouse = model
    return model
  }

  override suspend fun delete(context: BeContext): HouseModel {
    val id = context.requestHouseId
    if (id == HouseIdModel.NONE) throw RepoWrongIdException(id.id)
    val model = cache.peekAndRemove(id.id)?.toModel() ?: throw RepoNotFoundException(id.id)
    context.responseHouse = model
    return model
  }

  override suspend fun list(context: BeContext): Collection<HouseModel> {
    val textFilter = context.houseFilter.text
    if (textFilter.length < 3) throw RepoIndexException(textFilter)
    val records = cache.asMap().filterValues {
      it.address?.contains(textFilter, true) ?: false
    }.values
    if (records.count() <= context.houseFilter.offset)
      throw RepoIndexException(textFilter)
    val list = records.toList()
      .subList(
        context.houseFilter.offset,
        if (records.count() >= context.houseFilter.offset + context.houseFilter.count)
          context.houseFilter.offset + context.houseFilter.count
        else records.count()
      ).map { it.toModel() }
    context.responseHouses = list.toMutableList()
    context.pageCount = list.count().takeIf { it != 0 }
      ?.let { (records.count().toDouble() / it + 0.5).toInt() }
      ?: Int.MIN_VALUE
    return list
  }

  private suspend fun save(dto: HouseInMemoryDto): HouseInMemoryDto {
    cache.put(dto.id, dto)
    return cache.get(dto.id)
  }
}
