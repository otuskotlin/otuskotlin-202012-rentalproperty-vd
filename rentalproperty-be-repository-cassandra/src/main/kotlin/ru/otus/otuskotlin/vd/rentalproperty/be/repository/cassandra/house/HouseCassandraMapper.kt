package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.house

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace
import com.datastax.oss.driver.api.mapper.annotations.DaoTable
import com.datastax.oss.driver.api.mapper.annotations.Mapper

@Mapper
interface HouseCassandraMapper {

  @DaoFactory
  fun houseByIdDao(
    @DaoKeyspace keyspace: String,
    @DaoTable table: String
  ): HouseByIdCassandraDao

  @DaoFactory
  fun houseByAddressDao(
    @DaoKeyspace keyspace: String,
    @DaoTable table: String
  ): HouseByAddressCassandraDao
}
