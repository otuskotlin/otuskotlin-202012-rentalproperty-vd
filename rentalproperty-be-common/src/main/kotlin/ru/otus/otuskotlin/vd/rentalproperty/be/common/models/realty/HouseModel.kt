package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseMaterialModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.HouseTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.InfrastructureModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.PlotStatusModel

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
    val STUB_SINGLE_HOUSE = HouseModel(
      id = HouseIdModel("test-advert-id"),
      address = "test-address",
      area = 160.4,
      material = HouseMaterialModel.STUB_BRICK,
      type = HouseTypeModel.STUB_SINGLE_HOUSE,
      series = "",
      floors = 2,
      areaPlot = 15.0,
      plotStatus = PlotStatusModel.STUB_IRP,
      infrastructure = mutableSetOf(
        InfrastructureModel.STUB_GAZ,
        InfrastructureModel.STUB_ELECTRICITY,
        InfrastructureModel.STUB_SEWERAGE,
        InfrastructureModel.STUB_WATER,
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
    val STUB_MULTI_APARTMENT = HouseModel(
      id = HouseIdModel("test-house-id"),
      address = "test-address",
      area = 0.0,
      material = HouseMaterialModel.STUB_BRICK,
      type = HouseTypeModel.STUB_MULTI_APARTMENT,
      series = "",
      floors = 5,
      areaPlot = 0.0,
      plotStatus = PlotStatusModel.NONE,
      infrastructure = mutableSetOf(
        InfrastructureModel.STUB_GAZ,
        InfrastructureModel.STUB_ELECTRICITY,
        InfrastructureModel.STUB_SEWERAGE,
        InfrastructureModel.STUB_WATER,
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
  }
}