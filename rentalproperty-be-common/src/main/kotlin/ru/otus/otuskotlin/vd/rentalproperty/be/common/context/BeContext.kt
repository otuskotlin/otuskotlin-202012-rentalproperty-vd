package ru.otus.otuskotlin.vd.rentalproperty.be.common.context

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IError
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertFlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertHouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.*
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.EmptyUserSession
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.IUserSession
import java.time.Instant

data class BeContext(
  var timeStarted: Instant = Instant.MIN,
  var responseId: String = "",
  var onRequest: String = "",
  var status: BeContextStatus = BeContextStatus.NONE,
  var errors: MutableList<IError> = mutableListOf(),
  var frameworkErrors: MutableList<Throwable> = mutableListOf(),
  var stubCase: StubCase = StubCase.NONE,

  val userSession: IUserSession<*> = EmptyUserSession,

  //Flat
  var requestFlatId: FlatIdModel = FlatIdModel.NONE,
  var requestFlat: FlatModel = FlatModel.NONE,
  var flatFilter: FlatFilterModel = FlatFilterModel.NONE,
  var responseFlat: FlatModel = FlatModel.NONE,
  var responseFlats: MutableList<FlatModel> = mutableListOf(),

  //House
  var requestHouseId: HouseIdModel = HouseIdModel.NONE,
  var requestHouse: HouseModel = HouseModel.NONE,
  var houseFilter: HouseFilterModel = HouseFilterModel.NONE,
  var responseHouse: HouseModel = HouseModel.NONE,
  var responseHouses: MutableList<HouseModel> = mutableListOf(),

  //Advert Flat
  var requestAdvertFlatId: AdvertIdModel = AdvertIdModel.NONE,
  var requestAdvertFlat: AdvertFlatModel = AdvertFlatModel.NONE,
  var advertFlatFilter: AdvertFilterModel = AdvertFilterModel.NONE,
  var responseAdvertFlat: AdvertFlatModel = AdvertFlatModel.NONE,
  var responseAdvertFlats: MutableList<AdvertFlatModel> = mutableListOf(),

  //Advert House
  var requestAdvertHouseId: AdvertIdModel = AdvertIdModel.NONE,
  var requestAdvertHouse: AdvertHouseModel = AdvertHouseModel.NONE,
  var advertHouseFilter: AdvertFilterModel = AdvertFilterModel.NONE,
  var responseAdvertHouse: AdvertHouseModel = AdvertHouseModel.NONE,
  var responseAdvertHouses: MutableList<AdvertHouseModel> = mutableListOf(),
)
