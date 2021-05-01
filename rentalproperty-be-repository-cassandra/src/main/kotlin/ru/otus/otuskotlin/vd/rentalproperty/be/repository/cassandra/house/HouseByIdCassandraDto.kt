package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.house

import com.datastax.oss.driver.api.mapper.annotations.CqlName
import com.datastax.oss.driver.api.mapper.annotations.Entity
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseMaterialModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.InfrastructureModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.PlotStatusModel
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common.dto.DirectoryCassandraDto
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common.dto.MediaFileCassandraDto
import java.util.*

@Entity
data class HouseByIdCassandraDto(
  @PartitionKey
  @CqlName(ID)
  val id: String? = null,
  @CqlName(ADDRESS)
  val address: String? = null,
  @CqlName(AREA)
  val area: Double? = null,
  @CqlName(MATERIAL)
  val material: DirectoryCassandraDto? = null,
  @CqlName(TYPE)
  val type: DirectoryCassandraDto? = null,
  @CqlName(SERIES)
  val series: String? = null,
  @CqlName(FLOORS)
  val floors: Int? = null,
  @CqlName(AREA_PLOT)
  val areaPlot: Double? = null,
  @CqlName(PLOT_STATUS)
  val plotStatus: DirectoryCassandraDto? = null,
  @CqlName(INFRASTRUCTURE)
  val infrastructure: Set<DirectoryCassandraDto>? = null,
  @CqlName(YEAR_CONSTRUCTION)
  val yearConstruction: Int? = null,
  @CqlName(GARBAGE_CHUTE)
  val garbageChute: Boolean? = null,
  @CqlName(UNIT_ON_FLOOR)
  val unitOnFloor: Int? = null,
  @CqlName(PASSENGER_ELEVATOR)
  val passengerElevator: Int? = null,
  @CqlName(SERVICE_ELEVATOR)
  val serviceElevator: Int? = null,
  @CqlName(METRO)
  val metro: String? = null,
  @CqlName(TIME_TO_METRO)
  val timeToMetro: Int? = null,
  @CqlName(DISTANCE_TO_METRO)
  val distanceToMetro: Int? = null,
  @CqlName(PHOTOS)
  val photos: Set<MediaFileCassandraDto>? = null,
  @CqlName(LOCK_VERSION)
  val lockVersion: String? = null,
) {
  fun toModel() = HouseModel(
    id = id?.let { HouseIdModel(it) } ?: HouseModel.NONE.id,
    address = address ?: HouseModel.NONE.address,
    area = area ?: HouseModel.NONE.area,
    material = (material?.toModel()) as HouseMaterialModel,
    type = (type?.toModel()) as HouseTypeModel,
    series = series ?: HouseModel.NONE.series,
    floors = floors ?: HouseModel.NONE.floors,
    areaPlot = areaPlot ?: HouseModel.NONE.areaPlot,
    plotStatus = (plotStatus?.toModel()) as PlotStatusModel,
    infrastructure = infrastructure?.map { (it.toModel()) as InfrastructureModel }
      ?.toMutableSet() ?: HouseModel.NONE.infrastructure,
    yearConstruction = yearConstruction ?: HouseModel.NONE.yearConstruction,
    garbageChute = garbageChute ?: HouseModel.NONE.garbageChute,
    unitOnFloor = unitOnFloor ?: HouseModel.NONE.unitOnFloor,
    passengerElevator = passengerElevator ?: HouseModel.NONE.passengerElevator,
    serviceElevator = serviceElevator ?: HouseModel.NONE.serviceElevator,
    metro = metro ?: HouseModel.NONE.metro,
    timeToMetro = timeToMetro ?: HouseModel.NONE.timeToMetro,
    distanceToMetro = distanceToMetro ?: HouseModel.NONE.distanceToMetro,
    photos = photos?.map { it.toModel() }
      ?.toMutableSet() ?: HouseModel.NONE.photos,
  )

  companion object {
    const val TABLE_NAME = "houses_by_id"
    const val ID = "id"
    const val ADDRESS = "address"
    const val AREA = "area"
    const val MATERIAL = "material"
    const val TYPE = "type"
    const val SERIES = "series"
    const val FLOORS = "floors"
    const val AREA_PLOT = "area_plot"
    const val PLOT_STATUS = "plot_status"
    const val INFRASTRUCTURE = "infrastructure"
    const val YEAR_CONSTRUCTION = "year_construction"
    const val GARBAGE_CHUTE = "garbage_chute"
    const val UNIT_ON_FLOOR = "unit_on_floor"
    const val PASSENGER_ELEVATOR = "passenger_elevator"
    const val SERVICE_ELEVATOR = "service_elevator"
    const val METRO = "metro"
    const val TIME_TO_METRO = "time_to_metro"
    const val DISTANCE_TO_METRO = "distance_to_metro"
    const val PHOTOS = "photos"
    const val LOCK_VERSION = "lock_version"

    fun of(model: HouseModel) = of(model, model.id.id)

    fun of(model: HouseModel, id: String) = HouseByIdCassandraDto(
      id = id.takeIf { it != HouseModel.NONE.id.id },
      address = model.address.takeIf { it != HouseModel.NONE.address },
      area = model.area.takeIf { it != HouseModel.NONE.area },
      material = model.material.takeIf { it != HouseModel.NONE.material }
        ?.let { DirectoryCassandraDto.of(it) },
      type = model.type.takeIf { it != HouseModel.NONE.type }
        ?.let { DirectoryCassandraDto.of(it) },
      series = model.series.takeIf { it != HouseModel.NONE.series },
      floors = model.floors.takeIf { it != HouseModel.NONE.floors },
      areaPlot = model.areaPlot.takeIf { it != HouseModel.NONE.areaPlot },
      plotStatus = model.plotStatus.takeIf { it != HouseModel.NONE.plotStatus }
        ?.let { DirectoryCassandraDto.of(it) },
      infrastructure = model.infrastructure.takeIf { it != HouseModel.NONE.infrastructure }
        ?.map { DirectoryCassandraDto.of(it) }?.toSet(),
      yearConstruction = model.yearConstruction.takeIf { it != HouseModel.NONE.yearConstruction },
      garbageChute = model.garbageChute.takeIf { it != HouseModel.NONE.garbageChute },
      unitOnFloor = model.unitOnFloor.takeIf { it != HouseModel.NONE.unitOnFloor },
      passengerElevator = model.passengerElevator.takeIf { it != HouseModel.NONE.passengerElevator },
      serviceElevator = model.serviceElevator.takeIf { it != HouseModel.NONE.serviceElevator },
      metro = model.metro.takeIf { it != HouseModel.NONE.metro },
      timeToMetro = model.timeToMetro.takeIf { it != HouseModel.NONE.timeToMetro },
      distanceToMetro = model.distanceToMetro.takeIf { it != HouseModel.NONE.distanceToMetro },
      photos = model.photos.takeIf { it != HouseModel.NONE.photos }
        ?.map { MediaFileCassandraDto.of(it) }?.toSet(),
      lockVersion = UUID.randomUUID().toString(),
    )
  }
}
