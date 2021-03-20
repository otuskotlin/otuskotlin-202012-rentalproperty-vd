package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.BathroomTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.ConvenienceTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.RepairTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.ViewFromWindowModel

data class FlatModel(
  val id: FlatIdModel = FlatIdModel.NONE,
  val houseId: HouseIdModel = HouseIdModel.NONE,
  val area: Double = 0.0,
  val rooms: Int = 0,
  val floor: Int = 0,
  val areaLiving: Double = 0.0,
  val areaKitchen: Double = 0.0,
  val ceilingHeight: Double = 0.0,  //высота потолков
  val numberResidents: Int = 0,     //допустимое количество жильцов
  val bedrooms: Int = 0,
  val beds: Int = 0,
  val bathroom: Int = 0,
  val bathroomType: BathroomTypeModel = BathroomTypeModel.NONE,
  val balcony: Int = 0,
  val loggia: Int = 0,
  val repairType: RepairTypeModel = RepairTypeModel.NONE,
  val noSmoking: Boolean = false,
  val noAnimals: Boolean = false,
  val noChildren: Boolean = false,
  val description: String = "",
  val viewFromWindow: ViewFromWindowModel = ViewFromWindowModel.NONE,
  val conveniences: MutableSet<ConvenienceTypeModel> = mutableSetOf(), //удобства
  val photos: MutableSet<MediaFileModel> = mutableSetOf(),
) {
  companion object {
    val NONE = FlatModel()
  }
}