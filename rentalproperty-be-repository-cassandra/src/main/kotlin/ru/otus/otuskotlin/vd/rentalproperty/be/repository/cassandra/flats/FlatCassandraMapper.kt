package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.flats

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace
import com.datastax.oss.driver.api.mapper.annotations.DaoTable
import com.datastax.oss.driver.api.mapper.annotations.Mapper

@Mapper
interface FlatCassandraMapper {

  @DaoFactory
  fun flatByIdDao(
    @DaoKeyspace keyspace: String,
    @DaoTable table: String
  ): FlatByIdCassandraDao

  @DaoFactory
  fun flatByDescriptionDao(
    @DaoKeyspace keyspace: String,
    @DaoTable table: String
  ): FlatByDescriptionCassandraDao
}
