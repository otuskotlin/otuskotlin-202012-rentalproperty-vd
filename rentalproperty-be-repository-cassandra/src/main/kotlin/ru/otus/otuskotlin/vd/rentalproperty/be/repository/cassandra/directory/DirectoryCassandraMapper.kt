package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.directory

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace
import com.datastax.oss.driver.api.mapper.annotations.DaoTable
import com.datastax.oss.driver.api.mapper.annotations.Mapper

@Mapper
interface DirectoryCassandraMapper {

  @DaoFactory
  fun directoryByIdDao(
    @DaoKeyspace keyspace: String,
    @DaoTable table: String
  ): DirectoryByIdCassandraDao

  @DaoFactory
  fun directoryByTypeDao(
    @DaoKeyspace keyspace: String,
    @DaoTable table: String
  ): DirectoryByTypeCassandraDao

}
