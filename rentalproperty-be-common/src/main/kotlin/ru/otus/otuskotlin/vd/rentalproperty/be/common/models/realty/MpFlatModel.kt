package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.media.MpMediaFileModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.enums.*

data class MpFlatModel(
   val id: MpFlatIdModel = MpFlatIdModel.NONE,
   val realtyType: RealtyTypeEnum = RealtyTypeEnum.FLAT,
   val price: Double,
   val area: Double,
   val address: String,
   val houseId: MpHouseIdModel,
   val rooms: Int,
   val floor: Int = 0,
   val areaLiving: Double = 0.0,
   val areaKitchen: Double = 0.0,
   val ceilingHeight: Double = 0.0,  //высота потолков
   val conveniences: MutableSet<ConvenienceEnum> = mutableSetOf(),
   val numberResidents: Int = 0,
   val photos: MutableSet<MpMediaFileModel> = mutableSetOf(),
   val bedrooms: Int = 0,
   val beds: Int = 0,
   val bathroom: Int = 0,
   val bathroomType: BathroomTypeEnum? = null,
   val balcony: Int = 0,
   val loggia: Int = 0,
   val repairType: RepairTypeEnum? = null,
   val noSmoking: Boolean = false,
   val noAnimals: Boolean = false,
   val noChildren: Boolean = false,
   val viewFromWindow: ViewFromWindowEnum? = null,
   val description: String = "",
)