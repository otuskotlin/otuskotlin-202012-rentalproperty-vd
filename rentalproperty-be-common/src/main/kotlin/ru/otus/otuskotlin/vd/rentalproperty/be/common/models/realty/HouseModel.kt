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
  val area: Double = 0.0,
  val address: String = "",
  val material: HouseMaterialModel = HouseMaterialModel.NONE,
  val type: HouseTypeModel = HouseTypeModel.NONE,
  val series: String = "",     //серия дома
  val floors: Int = 0,
  val areaPlot: Double = 0.0,  //площадь участка
  val plotStatus: PlotStatusModel = PlotStatusModel.NONE,
  val infrastructure: MutableSet<InfrastructureModel> = mutableSetOf(),
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