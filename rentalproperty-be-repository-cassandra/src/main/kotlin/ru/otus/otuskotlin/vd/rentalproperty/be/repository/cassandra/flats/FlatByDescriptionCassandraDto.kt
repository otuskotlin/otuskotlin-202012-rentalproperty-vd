package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.flats

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn
import com.datastax.oss.driver.api.mapper.annotations.CqlName
import com.datastax.oss.driver.api.mapper.annotations.Entity
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import java.time.Instant

@Entity
data class FlatByDescriptionCassandraDto(
  @PartitionKey(0)
  @CqlName(DESCRIPTION)
  val description: String? = null,
  @ClusteringColumn(0)
  @CqlName(TIMESTAMP)
  val timestamp: Instant? = null,
  @ClusteringColumn(1)
  @CqlName(ID)
  val id: String? = null,
  @ClusteringColumn(2)
  @CqlName(DESCRIPTION_INDEX)
  val descriptionIndex: String? = null,
) {

  companion object {
    const val TABLE_NAME = "flats_by_description"
    const val ID = "id"
    const val DESCRIPTION = "description"
    const val DESCRIPTION_INDEX = "description_index"
    const val TIMESTAMP = "timestamp"

    fun of(model: FlatModel) = of(model, model.id.id)

    fun of(model: FlatModel, id: String) = FlatByDescriptionCassandraDto(
      id = id.takeIf { it != FlatModel.NONE.id.id },
      description = model.description.takeIf { it != FlatModel.NONE.description },
      descriptionIndex = model.description.takeIf { it != FlatModel.NONE.description },
      timestamp = Instant.now()
    )
  }
}
