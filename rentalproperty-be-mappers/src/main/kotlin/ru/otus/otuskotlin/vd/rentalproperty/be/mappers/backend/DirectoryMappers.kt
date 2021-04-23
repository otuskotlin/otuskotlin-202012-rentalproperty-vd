package ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.WorkMode
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.*
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.*
import java.time.Instant

fun BeContext.respondDirectoryItemList() =
  ResponseDirectoryItemList(
    responseId = responseId,
    onRequest = onRequest,
    endTime = Instant.now().toString(),
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    status = status.toTransport(),
    debug = ResponseDirectoryItemList.Debug(
      mode = workMode.takeIf { it != WorkMode.DEFAULT }?.toTransport()
    ),
    directoryItems = responseDirectoryItems.takeIf { it.isNotEmpty() }?.filter { it != DirectoryItemModel.NONE }
      ?.map { it.toTransport() },
  )

fun BeContext.respondDirectoryItemCreate() =
  ResponseDirectoryItemCreate(
    responseId = responseId,
    onRequest = onRequest,
    endTime = Instant.now().toString(),
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    status = status.toTransport(),
    debug = ResponseDirectoryItemCreate.Debug(
      mode = workMode.takeIf { it != WorkMode.DEFAULT }?.toTransport()
    ),
    directoryItem = responseDirectoryItem.takeIf { it != DirectoryItemModel.NONE }?.toTransport(),
  )

fun BeContext.respondDirectoryItemRead() =
  ResponseDirectoryItemRead(
    responseId = responseId,
    onRequest = onRequest,
    endTime = Instant.now().toString(),
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    status = status.toTransport(),
    debug = ResponseDirectoryItemRead.Debug(
      mode = workMode.takeIf { it != WorkMode.DEFAULT }?.toTransport()
    ),
    directoryItem = responseDirectoryItem.takeIf { it != DirectoryItemModel.NONE }?.toTransport(),
  )

fun BeContext.respondDirectoryItemUpdate() =
  ResponseDirectoryItemUpdate(
    responseId = responseId,
    onRequest = onRequest,
    endTime = Instant.now().toString(),
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    status = status.toTransport(),
    debug = ResponseDirectoryItemUpdate.Debug(
      mode = workMode.takeIf { it != WorkMode.DEFAULT }?.toTransport()
    ),
    directoryItem = responseDirectoryItem.takeIf { it != DirectoryItemModel.NONE }?.toTransport(),
  )

fun BeContext.respondDirectoryItemDelete() =
  ResponseDirectoryItemDelete(
    responseId = responseId,
    onRequest = onRequest,
    endTime = Instant.now().toString(),
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    status = status.toTransport(),
    debug = ResponseDirectoryItemDelete.Debug(
      mode = workMode.takeIf { it != WorkMode.DEFAULT }?.toTransport()
    ),
    directoryItem = responseDirectoryItem.takeIf { it != DirectoryItemModel.NONE }?.toTransport(),
  )

internal fun IDirectoryItemModel.toTransport() = DirectoryItemDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun IDirectoryDto.toModel() = DirectoryItemModel(
  id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
  name = name ?: ""
)

//Appliances
internal fun AppliancesModel.toTransport() = AppliancesDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun AppliancesDto.toModel() = AppliancesModel(
  id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
  name = name ?: ""
)

//BathroomType
internal fun BathroomTypeModel.toTransport() =
  BathroomTypeDto(
    id = id.id.takeIf { it.isNotBlank() },
    name = name
  )

internal fun BathroomTypeDto.toModel() = BathroomTypeModel(
  id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
  name = name ?: ""
)

//Convenience
internal fun ConvenienceModel.toTransport() = ConveniencesDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun ConveniencesDto.toModel() = ConvenienceModel(
  id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
  name = name ?: ""
)

//HouseMaterial
internal fun HouseMaterialModel.toTransport() = HouseMaterialDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun HouseMaterialDto.toModel() = HouseMaterialModel(
  id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
  name = name ?: ""
)

//HouseType
internal fun HouseTypeModel.toTransport() = HouseTypeDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun HouseTypeDto.toModel() = HouseTypeModel(
  id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
  name = name ?: ""
)

//Infrastructure
internal fun InfrastructureModel.toTransport() = InfrastructureDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun InfrastructureDto.toModel() = InfrastructureModel(
  id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
  name = name ?: ""
)

//PlotStatus
internal fun PlotStatusModel.toTransport() = PlotStatusDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun PlotStatusDto.toModel() = PlotStatusModel(
  id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
  name = name ?: ""
)

//RealtyType
internal fun RealtyTypeModel.toTransport() = RealtyTypeDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun RealtyTypeDto.toModel() = RealtyTypeModel(
  id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
  name = name ?: ""
)

//RepairType
internal fun RepairTypeModel.toTransport() = RepairTypeDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun RepairTypeDto.toModel() = RepairTypeModel(
  id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
  name = name ?: ""
)

//ViewFromWindow
internal fun ViewFromWindowModel.toTransport() = ViewFromWindowDto(
  id = id.id.takeIf { it.isNotBlank() },
  name = name
)

internal fun ViewFromWindowDto.toModel() = ViewFromWindowModel(
  id = id?.let { DirectoryItemIdModel(it) } ?: DirectoryItemIdModel.NONE,
  name = name ?: ""
)