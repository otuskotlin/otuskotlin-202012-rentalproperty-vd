package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.*

/**
 * House parameters
 */
data class HouseModel(
  val id: HouseIdModel = HouseIdModel.NONE,
  val realtyType: RealtyTypeEnum = RealtyTypeEnum.HOUSE,
  val price: Double = 0.0,
  val area: Double = 0.0,
  val address: String = "",
  val material: HouseMaterialEnum = HouseMaterialEnum.NONE,
  val type: HouseTypeEnum = HouseTypeEnum.NONE,
  val series: String = "",     //Серия дома
  val floors: Int = 0,
  val areaPlot: Double = 0.0,  //площадь участка
  val plotStatus: PlotStatusEnum? = null,
  val infrastructure: MutableSet<InfrastructureEnum> = mutableSetOf(),
  val yearConstruction: Int = 0,
  val garbageChute: Boolean = false, //мусоропровод
  val unitOnFloor: Int = 0,          //квартир на этаже
  val passengerElevator: Int = 0,
  val serviceElevator: Int = 0,
  val metro: String = "",
  val timeToMetro: Int = 0,
  val distanceToMetro: Int = 0,
  val photos: MutableSet<MediaFileModel> = mutableSetOf(),
) {
  companion object {
    val NONE = HouseModel()
  }
}