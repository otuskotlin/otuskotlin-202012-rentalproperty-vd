package ru.otus.otuskotlin.vd.rentalproperty.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.directory.*

//BathroomType
internal fun BathroomTypeModel.toTransport() = BathroomTypeDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun BathroomTypeDto.toModel() = BathroomTypeModel(
  id = id?.let { DirectoryIdModel(it) }
    ?: DirectoryIdModel.NONE,
  name = name ?: ""
)

//ConvenienceType
internal fun ConvenienceTypeModel.toTransport() = ConvenienceTypeDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun ConvenienceTypeDto.toModel() = ConvenienceTypeModel(
  id = id?.let { DirectoryIdModel(it) }
    ?: DirectoryIdModel.NONE,
  name = name ?: ""
)

//HouseMaterial
internal fun HouseMaterialModel.toTransport() = HouseMaterialDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun HouseMaterialDto.toModel() = HouseMaterialModel(
  id = id?.let { DirectoryIdModel(it) }
    ?: DirectoryIdModel.NONE,
  name = name ?: ""
)

//HouseType
internal fun HouseTypeModel.toTransport() = HouseTypeDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun HouseTypeDto.toModel() = HouseTypeModel(
  id = id?.let { DirectoryIdModel(it) }
    ?: DirectoryIdModel.NONE,
  name = name ?: ""
)

//Infrastructure
internal fun InfrastructureModel.toTransport() = InfrastructureDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun InfrastructureDto.toModel() = InfrastructureModel(
  id = id?.let { DirectoryIdModel(it) }
    ?: DirectoryIdModel.NONE,
  name = name ?: ""
)

//PlotStatus
internal fun PlotStatusModel.toTransport() = PlotStatusDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun PlotStatusDto.toModel() = PlotStatusModel(
  id = id?.let { DirectoryIdModel(it) }
    ?: DirectoryIdModel.NONE,
  name = name ?: ""
)

//RealtyType
internal fun RealtyTypeModel.toTransport() = RealtyTypeDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun RealtyTypeDto.toModel() = RealtyTypeModel(
  id = id?.let { DirectoryIdModel(it) }
    ?: DirectoryIdModel.NONE,
  name = name ?: ""
)

//RepairType
internal fun RepairTypeModel.toTransport() = RepairTypeDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun RepairTypeDto.toModel() = RepairTypeModel(
  id = id?.let { DirectoryIdModel(it) }
    ?: DirectoryIdModel.NONE,
  name = name ?: ""
)

//ViewFromWindow
internal fun ViewFromWindowModel.toTransport() = ViewFromWindowDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun ViewFromWindowDto.toModel() = ViewFromWindowModel(
  id = id?.let { DirectoryIdModel(it) }
    ?: DirectoryIdModel.NONE,
  name = name ?: ""
)