package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.house

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn
import com.datastax.oss.driver.api.mapper.annotations.CqlName
import com.datastax.oss.driver.api.mapper.annotations.Entity
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import java.time.Instant

@Entity
data class HouseByAddressCassandraDto(
  @PartitionKey(0)
  @CqlName(ADDRESS)
  val address: String? = null,
  @ClusteringColumn(0)
  @CqlName(TIMESTAMP)
  val timestamp: Instant? = null,
  @ClusteringColumn(1)
  @CqlName(ID)
  val id: String? = null,
  @ClusteringColumn(2)
  @CqlName(ADDRESS_INDEX)
  val addressIndex: String? = null,
) {

  companion object {
    const val TABLE_NAME = "houses_by_address"
    const val ID = "id"
    const val ADDRESS = "address"
    const val ADDRESS_INDEX = "address_index"
    const val TIMESTAMP = "timestamp"

    fun of(model: HouseModel) = of(model, model.id.id)

    fun of(model: HouseModel, id: String) = HouseByAddressCassandraDto(
      id = id.takeIf { it != HouseModel.NONE.id.id },
      address = model.address.takeIf { it != HouseModel.NONE.address },
      addressIndex = model.address.takeIf { it != HouseModel.NONE.address },
      timestamp = Instant.now()
    )
  }
}
