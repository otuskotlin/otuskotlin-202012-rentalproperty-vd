package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.directory

import com.datastax.oss.driver.api.mapper.annotations.*
import com.google.common.util.concurrent.ListenableFuture

@Dao
interface DirectoryByIdCassandraDao {
  @Insert
  @StatementAttributes(consistencyLevel = "ONE")
  fun createAsync(dto: DirectoryByIdCassandraDto): ListenableFuture<Unit>

  @Select
  fun readAsync(id: String): ListenableFuture<DirectoryByIdCassandraDto?>

  /**
   *  В данном случае условие в Update избыточно, так как обновляется вся модель.
   *  Может быть нужно при обновлении отдельных полей
   */
  @Update(customIfClause = "${DirectoryByIdCassandraDto.LOCK_VERSION} = :lock_key")
  @StatementAttributes(consistencyLevel = "QUORUM")
  fun updateAsync(dto: DirectoryByIdCassandraDto, @CqlName("lock_key") lockKey: String): ListenableFuture<Boolean>

  /**
   *  При удалении по ключу требуется указание [entityClass], при удалении по всей модели
   *  класс не требуется указывать, он берется из модели
   */
  @Delete(ifExists = true, entityClass = [DirectoryByIdCassandraDto::class])
  fun deleteAsync(id: String): ListenableFuture<Boolean>
}

@Dao
interface DirectoryByTypeCassandraDao {
  @Insert
  @StatementAttributes(consistencyLevel = "ONE")
  fun createAsync(dto: DirectoryByTypeCassandraDto): ListenableFuture<Unit>

  @Select(
    customWhereClause = "${DirectoryByTypeCassandraDto.TYPE_INDEX} LIKE :filter",
  )
  fun filterByTypeAsync(filter: String): ListenableFuture<Collection<DirectoryByTypeCassandraDto>>

  @Delete
  fun deleteAsync(dto: DirectoryByTypeCassandraDto): ListenableFuture<Unit>
}
