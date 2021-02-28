package ru.otus.otuskotlin.vd.rentalproperty.ktor.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.ktor.enums.*
import ru.otus.otuskotlin.vd.rentalproperty.ktor.models.media.MediaFile

data class MpHouseModel(
  override val id: MpHouseIdModel = MpHouseIdModel.NONE,
  override val realtyType: RealtyTypeEnum = RealtyTypeEnum.HOUSE,
  override val price: Double,
  override val area: Double,
  override val address: String,
  override val material: HouseMaterialEnum,
  override val type: HouseTypeEnum,
  override val series: String = "",     //Серия дома
  override val floors: Int = 0,
  override val areaPlot: Double = 0.0,  //площадь участка
  override val plotStatus: PlotStatusEnum? = null,
  override val infrastructure: MutableSet<InfrastructureEnum> = mutableSetOf(),
  override val yearConstruction: Int = 0,
  override val garbageChute: Boolean = false, //мусоропровод
  override val unitOnFloor: Int = 0,          //квартир на этаже
  override val passengerElevator: Int = 0,
  override val serviceElevator: Int = 0,
  override val metro: String = "",
  override val timeToMetro: Int = 0,
  override val distanceToMetro: Int = 0,
  override val photos: MutableSet<MediaFile> = mutableSetOf(),
) : IMpRealtyModel, IMpHouseModel