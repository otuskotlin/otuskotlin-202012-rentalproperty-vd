package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.house

import com.datastax.oss.driver.api.mapper.annotations.*
import com.google.common.util.concurrent.ListenableFuture

@Dao
interface HouseByIdCassandraDao {
  @Insert
  @StatementAttributes(consistencyLevel = "ONE")
  fun createAsync(dto: HouseByIdCassandraDto): ListenableFuture<Unit>

  @Select
  fun readAsync(id: String): ListenableFuture<HouseByIdCassandraDto?>

  /**
   *  В данном случае условие в Update избыточно, так как обновляется вся модель.
   *  Может быть нужно при обновлении отдельных полей
   */
  @Update(customIfClause = "${HouseByIdCassandraDto.LOCK_VERSION} = :lock_key")
  @StatementAttributes(consistencyLevel = "QUORUM")
  fun updateAsync(dto: HouseByIdCassandraDto, @CqlName("lock_key") lockKey: String): ListenableFuture<Boolean>

  /**
   *  При удалении по ключу требуется указание [entityClass], при удалении по всей модели
   *  класс не требуется указывать, он берется из модели
   */
  @Delete(ifExists = true, entityClass = [HouseByIdCassandraDto::class])
  fun deleteAsync(id: String): ListenableFuture<Boolean>
}

@Dao
interface HouseByAddressCassandraDao {
  @Insert
  @StatementAttributes(consistencyLevel = "ONE")
  fun createAsync(dto: HouseByAddressCassandraDto): ListenableFuture<Unit>

  @Select(
    customWhereClause = "${HouseByAddressCassandraDto.ADDRESS_INDEX} LIKE :filter",
  )
  fun filterByAddressAsync(filter: String): ListenableFuture<Collection<HouseByAddressCassandraDto>>

  @Delete
  fun deleteAsync(dto: HouseByAddressCassandraDto): ListenableFuture<Unit>
}
