package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.flats

import com.datastax.oss.driver.api.mapper.annotations.*
import com.google.common.util.concurrent.ListenableFuture

@Dao
interface FlatByIdCassandraDao {
  @Insert
  @StatementAttributes(consistencyLevel = "ONE")
  fun createAsync(dto: FlatByIdCassandraDto): ListenableFuture<Unit>

  @Select
  fun readAsync(id: String): ListenableFuture<FlatByIdCassandraDto?>

  /**
   *  В данном случае условие в Update избыточно, так как обновляется вся модель.
   *  Может быть нужно при обновлении отдельных полей
   */
  @Update(customIfClause = "${FlatByIdCassandraDto.LOCK_VERSION} = :lock_key")
  @StatementAttributes(consistencyLevel = "QUORUM")
  fun updateAsync(dto: FlatByIdCassandraDto, @CqlName("lock_key") lockKey: String): ListenableFuture<Boolean>

  /**
   *  При удалении по ключу требуется указание [entityClass], при удалении по всей модели
   *  класс не требуется указывать, он берется из модели
   */
  @Delete(ifExists = true, entityClass = [FlatByIdCassandraDto::class])
  fun deleteAsync(id: String): ListenableFuture<Boolean>
}

@Dao
interface FlatByTitleCassandraDao {
  @Insert
  @StatementAttributes(consistencyLevel = "ONE")
  fun createAsync(dto: FlatByTitleCassandraDto): ListenableFuture<Unit>

  @Select(
    customWhereClause = "${FlatByTitleCassandraDto.TITLE_INDEX} LIKE :filter",
  )
  fun filterByTitleAsync(filter: String): ListenableFuture<Collection<FlatByTitleCassandraDto>>
//
//    @Query("SELECT ${FlatByTitleCassandraDto.ID} FROM ${FlatByTitleCassandraDto.DEMANDS_TITLE_TABLE_NAME}" +
//            "WHERE ${FlatByTitleCassandraDto.TITLE} LIKE :filter ORDER BY ${FlatByTitleCassandraDto.TIMESTAMP} DESC")
//    fun filterByTitleAsync(filter: String): ListenableFuture<Collection<String>>

  @Delete
  fun deleteAsync(dto: FlatByTitleCassandraDto): ListenableFuture<Unit>
}
