package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MediaFileModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.ConvenienceModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.RepairTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.ViewFromWindowModel

class RoomModel(
  val id: RoomIdModel = RoomIdModel.NONE,
  val houseId: HouseIdModel = HouseIdModel.NONE,
  val area: Double = 0.0,
  val number: Int = 1,                //количество комнат
  val ceilingHeight: Double? = null,  //высота потолков
  val beds: Int = 0,
  val balcony: Int = 0,
  val loggia: Int = 0,
  val repairType: RepairTypeModel = RepairTypeModel.NONE,
  val noSmoking: Boolean = false,
  val noAnimals: Boolean = false,
  val noChildren: Boolean = false,
  val description: String = "",
  val viewFromWindow: ViewFromWindowModel = ViewFromWindowModel.NONE,
  val conveniences: MutableSet<ConvenienceModel> = mutableSetOf(),
  val photos: MutableSet<MediaFileModel> = mutableSetOf(),
) {
  companion object {
    val NONE = RoomModel()
  }
}