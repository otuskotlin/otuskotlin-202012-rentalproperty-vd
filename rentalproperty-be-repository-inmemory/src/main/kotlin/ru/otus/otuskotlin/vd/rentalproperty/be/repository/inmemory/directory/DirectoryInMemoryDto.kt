package ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.directory

import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.*

data class DirectoryInMemoryDto(
  val id: String? = null,
  val name: String? = null,
  val type: String? = null,
) {
  fun toModel() = when (type) {
    TypeDirectory.Appliances.name -> AppliancesModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
      name = name ?: "",
    )
    TypeDirectory.BathroomType.name -> BathroomTypeModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
      name = name ?: "",
    )
    TypeDirectory.Convenience.name -> ConvenienceModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
      name = name ?: "",
    )
    TypeDirectory.HouseMaterial.name -> HouseMaterialModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
      name = name ?: "",
    )
    TypeDirectory.HouseType.name -> HouseTypeModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
      name = name ?: "",
    )
    TypeDirectory.Infrastructure.name -> InfrastructureModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
      name = name ?: "",
    )
    TypeDirectory.PlotStatus.name -> PlotStatusModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
      name = name ?: "",
    )
    TypeDirectory.RealtyType.name -> RealtyTypeModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
      name = name ?: "",
    )
    TypeDirectory.RepairType.name -> RepairTypeModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
      name = name ?: "",
    )
    TypeDirectory.ViewFromWindow.name -> ViewFromWindowModel(
      id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
      name = name ?: "",
    )
    else -> DirectoryItemModel()
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

  companion object {
    fun of(model: IDirectoryItemModel) = of(model, model.id.id)

    fun of(model: IDirectoryItemModel, id: String) = DirectoryInMemoryDto(
      id = id.takeIf { it.isNotBlank() },
      name = model.name.takeIf { it.isNotBlank() },
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
}
