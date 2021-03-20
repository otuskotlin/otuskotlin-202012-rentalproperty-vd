package ru.otus.otuskotlin.vd.rentalproperty.be.common.context

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertRentHouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel

data class BeContext(
  //House
  var requestHouseId: HouseIdModel = HouseIdModel.NONE,
  var requestHouse: HouseModel = HouseModel.NONE,

  var houseFilter: HouseFilterModel = HouseFilterModel.NONE,

  var responseHouse: HouseModel = HouseModel.NONE,
  var responseHouses: MutableList<HouseModel> = mutableListOf(),

  //Advert
  var requestAdvertRentHouseId: AdvertIdModel = AdvertIdModel.NONE,
  var requestAdvertRentHouse: AdvertRentHouseModel = AdvertRentHouseModel.NONE,

  var advertFilter: AdvertFilterModel = AdvertFilterModel.NONE,

  var responseAdvertRentHouse: AdvertRentHouseModel = AdvertRentHouseModel.NONE,
  var responseAdvertRentHouses: MutableList<AdvertRentHouseModel> = mutableListOf(),
)
