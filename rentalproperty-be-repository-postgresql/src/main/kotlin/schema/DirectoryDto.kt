package schema

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.*
import java.util.*

class DirectoryDto(id: EntityID<UUID>) : UUIDEntity(id) {
  var name by DirectoriesTable.name
  var type by DirectoriesTable.type

  fun toModel() = when (type) {
    TypeDirectory.Appliances.name -> AppliancesModel(
      id = DirectoryItemIdModel(id.value.toString()),
      name = name ?: "",
    )
    TypeDirectory.BathroomType.name -> BathroomTypeModel(
      id = DirectoryItemIdModel(id.value.toString()),
      name = name ?: "",
    )
    TypeDirectory.Convenience.name -> ConvenienceModel(
      id = DirectoryItemIdModel(id.value.toString()),
      name = name ?: "",
    )
    TypeDirectory.HouseMaterial.name -> HouseMaterialModel(
      id = DirectoryItemIdModel(id.value.toString()),
      name = name ?: "",
    )
    TypeDirectory.HouseType.name -> HouseTypeModel(
      id = DirectoryItemIdModel(id.value.toString()),
      name = name ?: "",
    )
    TypeDirectory.Infrastructure.name -> InfrastructureModel(
      id = DirectoryItemIdModel(id.value.toString()),
      name = name ?: "",
    )
    TypeDirectory.PlotStatus.name -> PlotStatusModel(
      id = DirectoryItemIdModel(id.value.toString()),
      name = name ?: "",
    )
    TypeDirectory.RealtyType.name -> RealtyTypeModel(
      id = DirectoryItemIdModel(id.value.toString()),
      name = name ?: "",
    )
    TypeDirectory.RepairType.name -> RepairTypeModel(
      id = DirectoryItemIdModel(id.value.toString()),
      name = name ?: "",
    )
    TypeDirectory.ViewFromWindow.name -> ViewFromWindowModel(
      id = DirectoryItemIdModel(id.value.toString()),
      name = name ?: "",
    )
    else -> DirectoryItemModel()
  }

  fun of(model: IDirectoryItemModel) {
    name = model.name
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
  }

  companion object : UUIDEntityClass<DirectoryDto>(DirectoriesTable)

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
