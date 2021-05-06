package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.*

/**
 * Характиристики квартиры
 */
data class FlatModel(
  val id: FlatIdModel = FlatIdModel.NONE,
  val houseId: HouseIdModel = HouseIdModel.NONE,
  /** номер квартиры (для полного адреса) */
  val number: String = "",
  /** площадь квартиры */
  val area: Double = 0.0,
  /** жилая площадь */
  val areaLiving: Double = 0.0,
  /** площадь кухни */
  val areaKitchen: Double = 0.0,
  /** количество комнат */
  val rooms: Int = 0,
  /** этаж */
  val floor: Int = 0,
  /** высота потолков */
  val ceilingHeight: Double = 0.0,
  /** количество спальных комнат */
  val bedrooms: Int = 0,
  /** количество кроватей */
  val beds: Int = 0,
  /** количество ванных комнат */
  val bathrooms: Int = 0,
  /** тип ванной комнаты:
   *    "COMBINED" совмещённый, "SEPARATED" раздельный */
  val bathroomType: BathroomTypeModel = BathroomTypeModel.NONE,
  /** количество балконов */
  val balcony: Int = 0,
  /** количество лоджий */
  val loggia: Int = 0,
  /** вид ремонта:
   *    "RENOVATION" евроремонт
   *    "DESIGNER_RENOVATION" дизайнерский
   *    "REDECORATING" косметический */
  val repairType: RepairTypeModel = RepairTypeModel.NONE,
  /** вид из окна:
   *    "FOREST", "PARK", "POND", "STREET", "YARD"*/
  val viewFromWindow: ViewFromWindowModel = ViewFromWindowModel.NONE,
  /** удобства: "CONCIERGE",  "KINDERGARTEN",
   *    "FURNITURE_IN_KITCHEN", "PARK", "PARKING", "SPORTS_GROUND", ... */
  val conveniences: MutableSet<ConvenienceModel> = mutableSetOf(),
  /** техника: "AIR_CONDITIONER",  "DISHWASHER",  "FRIDGE",
   *    "INTERCOM", "INTERNET", "KITCHEN_STOVE", "TELEPHONE", ... */
  val appliances: MutableSet<AppliancesModel> = mutableSetOf(),
  /** допустимое количество жильцов */
  val residents: Int = 0,
  /** запрет на курение */
  val noSmoking: Boolean = false,
  /** запрет на присутствие животных */
  val noAnimals: Boolean = false,
  /** запрет на присутствие детей */
  val noChildren: Boolean = false,
  /** запрет на вечеринки */
  val noParties: Boolean = false,
  /** общее описание квартиры и условий */
  val description: String = "",
  /** фотографии квартиры */
  val photos: MutableSet<MediaFileModel> = mutableSetOf(),
) {
  companion object {
    val NONE = FlatModel()
    val STUB = FlatModel(
      id = FlatIdModel("test-flat-id-1"),
      houseId = HouseIdModel("test-house-id-1"),
      number = "95",
      area = 44.4,
      areaLiving = 28.0,
      areaKitchen = 4.0,
      rooms = 2,
      floor = 3,
      ceilingHeight = 2.5,
      bedrooms = 1,
      beds = 1,
      bathrooms = 1,
      bathroomType = BathroomTypeModel.STUB_COMBINED,
      balcony = 0,
      loggia = 0,
      repairType = RepairTypeModel.STUB_RENOVATION,
      viewFromWindow = ViewFromWindowModel.STUB_PARK,
      conveniences = mutableSetOf(
        ConvenienceModel.STUB_GAS,
        ConvenienceModel.STUB_FURNITURE_IN_KITCHEN,
        ConvenienceModel.STUB_PARKING,
      ),
      appliances = mutableSetOf(
        AppliancesModel.STUB_AIR_CONDITIONER,
        AppliancesModel.STUB_KITCHEN_STOVE,
        AppliancesModel.STUB_INTERNET,
      ),
      residents = 4,
      noSmoking = true,
      noAnimals = false,
      noChildren = false,
      noParties = true,
      description = "Хрущёвка",
      photos = mutableSetOf(),
    )
    val STUB2 = FlatModel(
      id = FlatIdModel("test-flat-id-2"),
      houseId = HouseIdModel("test-house-id-2"),
      number = "22",
      area = 52.0,
      areaLiving = 39.0,
      areaKitchen = 7.0,
      rooms = 3,
      floor = 2,
      ceilingHeight = 2.5,
      bedrooms = 2,
      beds = 2,
      bathrooms = 1,
      bathroomType = BathroomTypeModel.STUB_COMBINED,
      balcony = 1,
      loggia = 0,
      repairType = RepairTypeModel.STUB_RENOVATION,
      viewFromWindow = ViewFromWindowModel.STUB_PARK,
      conveniences = mutableSetOf(
        ConvenienceModel.STUB_FURNITURE_IN_KITCHEN,
        ConvenienceModel.STUB_PARKING,
      ),
      appliances = mutableSetOf(
        AppliancesModel.STUB_KITCHEN_STOVE,
        AppliancesModel.STUB_INTERNET,
      ),
      residents = 4,
      noSmoking = false,
      noAnimals = false,
      noChildren = false,
      noParties = false,
      description = "Улучшенка",
      photos = mutableSetOf(),
    )
    val STUB3 = FlatModel(
      id = FlatIdModel("test-flat-id-3"),
      houseId = HouseIdModel("test-house-id-3"),
      number = "33",
      area = 73.0,
      areaLiving = 56.0,
      areaKitchen = 8.0,
      rooms = 4,
      floor = 7,
      ceilingHeight = 3.0,
      bedrooms = 3,
      beds = 3,
      bathrooms = 1,
      bathroomType = BathroomTypeModel.STUB_COMBINED,
      balcony = 2,
      loggia = 0,
      repairType = RepairTypeModel.STUB_RENOVATION,
      viewFromWindow = ViewFromWindowModel.STUB_PARK,
      conveniences = mutableSetOf(
        ConvenienceModel.STUB_FURNITURE_IN_KITCHEN,
      ),
      appliances = mutableSetOf(
        AppliancesModel.STUB_KITCHEN_STOVE,
      ),
      residents = 4,
      noSmoking = false,
      noAnimals = false,
      noChildren = false,
      noParties = false,
      description = "Распашонка",
      photos = mutableSetOf(),
    )
  }
}