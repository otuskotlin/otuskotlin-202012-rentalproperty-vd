package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.directory

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn
import com.datastax.oss.driver.api.mapper.annotations.CqlName
import com.datastax.oss.driver.api.mapper.annotations.Entity
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.*
import java.time.Instant

@Entity
data class DirectoryByTypeCassandraDto(
  @PartitionKey(0)
  @CqlName(TYPE)
  val type: String? = null,
  @ClusteringColumn(0)
  @CqlName(TIMESTAMP)
  val timestamp: Instant? = null,
  @ClusteringColumn(1)
  @CqlName(ID)
  val id: String? = null,
  @ClusteringColumn(2)
  @CqlName(TYPE_INDEX)
  val typeIndex: String? = null,
) {

  companion object {
    const val TABLE_NAME = "directories_by_type"
    const val ID = "id"
    const val TYPE = "type"
    const val TYPE_INDEX = "type_index"
    const val TIMESTAMP = "timestamp"

    fun of(model: IDirectoryItemModel) = of(model, model.id.id)

    fun of(model: IDirectoryItemModel, id: String): DirectoryByTypeCassandraDto {
      val directoryByTypeCassandraDto = DirectoryByTypeCassandraDto(
        id = id.takeIf { it != DirectoryItemIdModel.NONE.id },
        type = when (model) {
          is AppliancesModel -> DirectoryByIdCassandraDto.TypeDirectory.Appliances.name
          is BathroomTypeModel -> DirectoryByIdCassandraDto.TypeDirectory.BathroomType.name
          is ConvenienceModel -> DirectoryByIdCassandraDto.TypeDirectory.Convenience.name
          is HouseMaterialModel -> DirectoryByIdCassandraDto.TypeDirectory.HouseMaterial.name
          is HouseTypeModel -> DirectoryByIdCassandraDto.TypeDirectory.HouseType.name
          is InfrastructureModel -> DirectoryByIdCassandraDto.TypeDirectory.Infrastructure.name
          is PlotStatusModel -> DirectoryByIdCassandraDto.TypeDirectory.PlotStatus.name
          is RealtyTypeModel -> DirectoryByIdCassandraDto.TypeDirectory.RealtyType.name
          is RepairTypeModel -> DirectoryByIdCassandraDto.TypeDirectory.RepairType.name
          is ViewFromWindowModel -> DirectoryByIdCassandraDto.TypeDirectory.ViewFromWindow.name
          else -> DirectoryByIdCassandraDto.TypeDirectory.NONE.name
        },
        typeIndex = when (model) {
          is AppliancesModel -> DirectoryByIdCassandraDto.TypeDirectory.Appliances.name
          is BathroomTypeModel -> DirectoryByIdCassandraDto.TypeDirectory.BathroomType.name
          is ConvenienceModel -> DirectoryByIdCassandraDto.TypeDirectory.Convenience.name
          is HouseMaterialModel -> DirectoryByIdCassandraDto.TypeDirectory.HouseMaterial.name
          is HouseTypeModel -> DirectoryByIdCassandraDto.TypeDirectory.HouseType.name
          is InfrastructureModel -> DirectoryByIdCassandraDto.TypeDirectory.Infrastructure.name
          is PlotStatusModel -> DirectoryByIdCassandraDto.TypeDirectory.PlotStatus.name
          is RealtyTypeModel -> DirectoryByIdCassandraDto.TypeDirectory.RealtyType.name
          is RepairTypeModel -> DirectoryByIdCassandraDto.TypeDirectory.RepairType.name
          is ViewFromWindowModel -> DirectoryByIdCassandraDto.TypeDirectory.ViewFromWindow.name
          else -> DirectoryByIdCassandraDto.TypeDirectory.NONE.name
        },
        timestamp = Instant.now()
      )
      return directoryByTypeCassandraDto
    }
  }
}
