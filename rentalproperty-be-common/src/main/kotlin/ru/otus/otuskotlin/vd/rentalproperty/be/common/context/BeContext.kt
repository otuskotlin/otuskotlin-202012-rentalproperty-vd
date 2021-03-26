package ru.otus.otuskotlin.vd.rentalproperty.be.common.context

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IError
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertHouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import java.time.Instant

data class BeContext(
  var timeStarted: Instant = Instant.MIN,
  var responseId: String = "",
  var onRequest: String = "",
  var status: BeContextStatus = BeContextStatus.NONE,
  var errors: MutableList<IError> = mutableListOf(),
  var frameworkErrors: MutableList<Throwable> = mutableListOf(),
  var stubCase: StubCase = StubCase.NONE,

  //House
  var requestHouseId: HouseIdModel = HouseIdModel.NONE,
  var requestHouse: HouseModel = HouseModel.NONE,
  var houseFilter: HouseFilterModel = HouseFilterModel.NONE,
  var responseHouse: HouseModel = HouseModel.NONE,
  var responseHouses: MutableList<HouseModel> = mutableListOf(),

  //Advert
  var requestAdvertHouseId: AdvertIdModel = AdvertIdModel.NONE,
  var requestAdvertHouse: AdvertHouseModel = AdvertHouseModel.NONE,
  var advertFilter: AdvertFilterModel = AdvertFilterModel.NONE,
  var responseAdvertHouse: AdvertHouseModel = AdvertHouseModel.NONE,
  var responseAdvertHouses: MutableList<AdvertHouseModel> = mutableListOf(),
)
