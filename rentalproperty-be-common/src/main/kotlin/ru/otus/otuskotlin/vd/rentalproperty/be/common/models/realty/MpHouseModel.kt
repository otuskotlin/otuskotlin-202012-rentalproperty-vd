package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

data class MpHouseModel(
  override val id: ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpHouseIdModel = ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpHouseIdModel.Companion.NONE,
  override val realtyType: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.RealtyTypeEnum = ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.RealtyTypeEnum.HOUSE,
  override val price: Double,
  override val area: Double,
  override val address: String,
  override val material: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseMaterialEnum,
  override val type: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.HouseTypeEnum,
  override val series: String = "",     //Серия дома
  override val floors: Int = 0,
  override val areaPlot: Double = 0.0,  //площадь участка
  override val plotStatus: ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.PlotStatusEnum? = null,
  override val infrastructure: MutableSet<ru.otus.otuskotlin.vd.rentalproperty.be.common.enums.InfrastructureEnum> = mutableSetOf(),
  override val yearConstruction: Int = 0,
  override val garbageChute: Boolean = false, //мусоропровод
  override val unitOnFloor: Int = 0,          //квартир на этаже
  override val passengerElevator: Int = 0,
  override val serviceElevator: Int = 0,
  override val metro: String = "",
  override val timeToMetro: Int = 0,
  override val distanceToMetro: Int = 0,
  override val photos: MutableSet<ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFile> = mutableSetOf(),
) : ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.IMpRealtyModel,
  ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.IMpHouseModel