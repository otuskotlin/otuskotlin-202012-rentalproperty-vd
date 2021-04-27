package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.flats

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn
import com.datastax.oss.driver.api.mapper.annotations.CqlName
import com.datastax.oss.driver.api.mapper.annotations.Entity
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import java.time.Instant

@Entity
data class FlatByTitleCassandraDto(
  @PartitionKey(0)
  @CqlName(TITLE)
  val title: String? = null,
  @ClusteringColumn(0)
  @CqlName(TIMESTAMP)
  val timestamp: Instant? = null,
  @ClusteringColumn(1)
  @CqlName(ID)
  val id: String? = null,
  @ClusteringColumn(2)
  @CqlName(TITLE_INDEX)
  val titleIndex: String? = null,
) {

  companion object {
    const val DEMANDS_TITLE_TABLE_NAME = "flats_by_title"
    const val ID = "id"
    const val TITLE = "title"
    const val TITLE_INDEX = "title_index"
    const val TIMESTAMP = "timestamp"

    fun of(model: FlatModel) = of(model, model.id.id)

    fun of(model: FlatModel, id: String) = FlatByTitleCassandraDto(
      id = id.takeIf { it != FlatModel.NONE.id.id },
      title = model.title.takeIf { it != FlatModel.NONE.title },
      titleIndex = model.title.takeIf { it != FlatModel.NONE.title },
      timestamp = Instant.now()
    )
  }
}
