package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common.dto

import com.datastax.oss.driver.api.mapper.annotations.CqlName
import com.datastax.oss.driver.api.mapper.annotations.Entity
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.*

@Entity
data class DirectoryCassandraDto(
  @CqlName(ID)
  val id: String? = null,
  @CqlName(NAME)
  val name: String? = null,
  @CqlName(TYPE)
  val type: String? = null,
) {
  fun toModel() = when (type) {
    TypeDirectory.Appliances.name -> AppliancesModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemModel.NONE.id,
      name = name ?: DirectoryItemModel.NONE.name,
    )
    TypeDirectory.BathroomType.name -> BathroomTypeModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemModel.NONE.id,
      name = name ?: DirectoryItemModel.NONE.name,
    )
    TypeDirectory.Convenience.name -> ConvenienceModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemModel.NONE.id,
      name = name ?: DirectoryItemModel.NONE.name,
    )
    TypeDirectory.HouseMaterial.name -> HouseMaterialModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemModel.NONE.id,
      name = name ?: DirectoryItemModel.NONE.name,
    )
    TypeDirectory.HouseType.name -> HouseTypeModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemModel.NONE.id,
      name = name ?: DirectoryItemModel.NONE.name,
    )
    TypeDirectory.Infrastructure.name -> InfrastructureModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemModel.NONE.id,
      name = name ?: DirectoryItemModel.NONE.name,
    )
    TypeDirectory.PlotStatus.name -> PlotStatusModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemModel.NONE.id,
      name = name ?: DirectoryItemModel.NONE.name,
    )
    TypeDirectory.RealtyType.name -> RealtyTypeModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemModel.NONE.id,
      name = name ?: DirectoryItemModel.NONE.name,
    )
    TypeDirectory.RepairType.name -> RepairTypeModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemModel.NONE.id,
      name = name ?: DirectoryItemModel.NONE.name,
    )
    TypeDirectory.ViewFromWindow.name -> ViewFromWindowModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemModel.NONE.id,
      name = name ?: DirectoryItemModel.NONE.name,
    )
    else -> DirectoryItemModel()
  }

  companion object {
    const val TYPE_NAME = "directory"
    const val ID = "id"
    const val NAME = "name"
    const val TYPE = "type"

    fun of(model: IDirectoryItemModel) = DirectoryCassandraDto(
      id = model.id.takeIf { it != DirectoryItemModel.NONE.id }?.id,
      name = model.name.takeIf { it != DirectoryItemModel.NONE.name },
      type = when (model) {
        is AppliancesModel -> TypeDirectory.Appliances.name
        is BathroomTypeModel -> TypeDirectory.BathroomType.name
        is ConvenienceModel -> TypeDirectory.Convenience.name
        is HouseMaterialModel -> TypeDirectory.HouseMaterial.name
        is HouseTypeModel -> TypeDirectory.HouseType.name
        is InfrastructureModel -> TypeDirectory.Infrastructure.name
        is PlotStatusModel -> TypeDirectory.PlotStatus.name
        is RealtyTypeModel -> TypeDirectory.RealtyType.name
        is RepairTypeModel -> TypeDirectory.RepairType.name
        is ViewFromWindowModel -> TypeDirectory.ViewFromWindow.name
        else -> TypeDirectory.NONE.name
      }
    )
  }

  enum class TypeDirectory {
    NONE,
    Appliances,
    BathroomType,
    Convenience,
    HouseMaterial,
    HouseType,
    Infrastructure,
    PlotStatus,
    RealtyType,
    RepairType,
    ViewFromWindow,
  }
}
