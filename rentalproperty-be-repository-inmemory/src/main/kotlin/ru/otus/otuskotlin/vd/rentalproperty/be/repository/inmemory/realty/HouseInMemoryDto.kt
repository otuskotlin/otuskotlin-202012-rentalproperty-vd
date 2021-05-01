package ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseMaterialModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.InfrastructureModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.PlotStatusModel
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.common.MediaFileInMemoryDto
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.inmemory.directory.DirectoryInMemoryDto

data class HouseInMemoryDto(
  val id: String? = null,
  val address: String? = null,
  val area: Double? = null,
  val material: DirectoryInMemoryDto? = null,
  val type: DirectoryInMemoryDto? = null,
  val series: String? = null,
  val floors: Int? = null,
  val areaPlot: Double? = null,
  val plotStatus: DirectoryInMemoryDto? = null,
  val infrastructure: Set<DirectoryInMemoryDto>? = null,
  val yearConstruction: Int? = null,
  val garbageChute: Boolean? = null,
  val unitOnFloor: Int? = null,
  val passengerElevator: Int? = null,
  val serviceElevator: Int? = null,
  val metro: String? = null,
  val timeToMetro: Int? = null,
  val distanceToMetro: Int? = null,
  val photos: Set<MediaFileInMemoryDto>? = null,
) {
  fun toModel() = HouseModel(
    id = id?.let { HouseIdModel(it) } ?: HouseIdModel.NONE,
    address = address ?: "",
    area = area ?: 0.0,
    material = (material?.toModel() ?: HouseMaterialModel.NONE) as HouseMaterialModel,
    type = (type?.toModel() ?: HouseTypeModel.NONE) as HouseTypeModel,
    series = series ?: "",
    floors = floors ?: 0,
    areaPlot = areaPlot ?: 0.0,
    plotStatus = (plotStatus?.toModel() ?: PlotStatusModel.NONE) as PlotStatusModel,
    infrastructure = infrastructure?.map { (it.toModel()) as InfrastructureModel }
      ?.toMutableSet() ?: mutableSetOf(),
    yearConstruction = yearConstruction ?: 0,
    garbageChute = garbageChute ?: false,
    unitOnFloor = unitOnFloor ?: 0,
    passengerElevator = passengerElevator ?: 0,
    serviceElevator = serviceElevator ?: 0,
    metro = metro ?: "",
    timeToMetro = timeToMetro ?: 0,
    distanceToMetro = distanceToMetro ?: 0,
    photos = photos?.map { it.toModel() }?.toMutableSet() ?: mutableSetOf(),
  )

  companion object {
    fun of(model: HouseModel) = of(model, model.id.id)

    fun of(model: HouseModel, id: String) = HouseInMemoryDto(
      id = id.takeIf { it.isNotBlank() },
      address = model.address.takeIf { it.isNotBlank() },
      area = model.area.takeIf { it != 0.0 },
      material = DirectoryInMemoryDto.of(model.material),
      type = DirectoryInMemoryDto.of(model.type),
      series = model.series.takeIf { it.isNotBlank() },
      floors = model.floors.takeIf { it != 0 },
      areaPlot = model.areaPlot.takeIf { it != 0.0 },
      plotStatus = DirectoryInMemoryDto.of(model.plotStatus),
      infrastructure = model.infrastructure.takeIf { it.isNotEmpty() }
        ?.map { DirectoryInMemoryDto.of(it) }?.toSet(),
      yearConstruction = model.yearConstruction.takeIf { it != 0 },
      garbageChute = model.garbageChute.takeIf { it },
      unitOnFloor = model.unitOnFloor.takeIf { it != 0 },
      passengerElevator = model.passengerElevator.takeIf { it != 0 },
      serviceElevator = model.serviceElevator.takeIf { it != 0 },
      metro = model.metro.takeIf { it.isNotBlank() },
      timeToMetro = model.timeToMetro.takeIf { it != 0 },
      distanceToMetro = model.distanceToMetro.takeIf { it != 0 },
      photos = model.photos.takeIf { it.isNotEmpty() }
        ?.filter { it != MediaFileModel.NONE }
        ?.map { MediaFileInMemoryDto.of(it) }?.toSet()
    )
  }
}
