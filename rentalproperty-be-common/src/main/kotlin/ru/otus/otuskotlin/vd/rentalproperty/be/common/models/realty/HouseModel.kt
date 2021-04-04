package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.*

/**
 * House parameters
 */
data class HouseModel(
  val id: HouseIdModel = HouseIdModel.NONE,
  /** адрес местонахождения*/
  val address: String = "",
  /** площадь дома */
  val area: Double = 0.0,
  /** матриал дома */
  val material: HouseMaterialModel = HouseMaterialModel.NONE,
  /** типа дома */
  val type: HouseTypeModel = HouseTypeModel.NONE,
  /** серия (многоквартирного) дома */
  val series: String = "",
  /** количество этажей в доме */
  val floors: Int = 0,
  /** площадь участка */
  val areaPlot: Double = 0.0,
  /** статус участка */
  val plotStatus: PlotStatusModel = PlotStatusModel.NONE,
  /** инфраструктура возле дома */
  val infrastructure: MutableSet<InfrastructureModel> = mutableSetOf(),
  /** год постройки дома */
  val yearConstruction: Int = 0,
  /** наличие мусоропровода */
  val garbageChute: Boolean = false,
  /** квартир/комнат на этаже */
  val unitOnFloor: Int = 0,
  /** количество пассажирских лифтов в доме */
  val passengerElevator: Int = 0,
  /** количество служебных лифтов в доме */
  val serviceElevator: Int = 0,
  /** наименование ближайшего метро */
  val metro: String = "",
  /** время в пути до ближайшего метро в минутах*/
  val timeToMetro: Int = 0,
  /** расстояние до ближайшего метро в метрах*/
  val distanceToMetro: Int = 0,
  /** фотографии дома */
  val photos: MutableSet<MediaFileModel> = mutableSetOf(),
) {
  companion object {
    val NONE = HouseModel()
    val STUB_MULTI_APARTMENT = HouseModel(
      id = HouseIdModel("test-id"),
      address = "test-address",
      area = 0.0,
      material = HouseMaterialModel(DirectoryIdModel("test-hm-id"), "BRICK"),
      type = HouseTypeModel(DirectoryIdModel("test-ht-id"), "MULTI_APARTMENT"),
      series = "",
      floors = 5,
      areaPlot = 0.0,
      plotStatus = PlotStatusModel.NONE,
      infrastructure = mutableSetOf(
        InfrastructureModel(DirectoryIdModel("test-im-id"), "GAZ"),
        InfrastructureModel(DirectoryIdModel("test-im-id"), "ELECTRICITY"),
        InfrastructureModel(DirectoryIdModel("test-im-id"), "SEWERAGE"),
        InfrastructureModel(DirectoryIdModel("test-im-id"), "WATER"),
      ),
      yearConstruction = 1975,
      garbageChute = false,
      unitOnFloor = 4,
      passengerElevator = 0,
      serviceElevator = 0,
      metro = "",
      timeToMetro = 0,
      distanceToMetro = 0,
      photos = mutableSetOf(),
    )
    val STUB_SINGLE_HOUSE = HouseModel(
      id = HouseIdModel("test-id"),
      address = "test-address",
      area = 160.4,
      material = HouseMaterialModel(DirectoryIdModel("test-hm-id"), "BRICK"),
      type = HouseTypeModel(DirectoryIdModel("test-ht-id"), "SINGLE_HOUSE"),
      series = "",
      floors = 2,
      areaPlot = 15.0,
      plotStatus = PlotStatusModel(DirectoryIdModel("test-psm-id"), "IRP"),
      infrastructure = mutableSetOf(
        InfrastructureModel(DirectoryIdModel("test-im-id"), "GAZ"),
        InfrastructureModel(DirectoryIdModel("test-im-id"), "ELECTRICITY"),
        InfrastructureModel(DirectoryIdModel("test-im-id"), "SEWERAGE"),
        InfrastructureModel(DirectoryIdModel("test-im-id"), "WATER"),
      ),
      yearConstruction = 2005,
      garbageChute = false,
      unitOnFloor = 3,
      passengerElevator = 0,
      serviceElevator = 0,
      metro = "",
      timeToMetro = 0,
      distanceToMetro = 0,
      photos = mutableSetOf(),
    )
  }
}