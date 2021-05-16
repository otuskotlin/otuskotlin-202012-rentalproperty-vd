package ru.otus.otuskotlin.vd.rentalproperty.be.common.context

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.IError
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.WorkMode
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertFlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertHouseModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert.AdvertIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.PrincipalModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.*
import ru.otus.otuskotlin.vd.rentalproperty.be.common.repositories.*
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryItemIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryItemModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.IDirectoryItemModel
import java.time.Instant

data class BeContext(
  var principal: PrincipalModel = PrincipalModel.NONE,

  var timeStarted: Instant = Instant.MIN,
  var responseId: String = "",
  var onRequest: String = "",
  var status: BeContextStatus = BeContextStatus.NONE,

  var errors: MutableList<IError> = mutableListOf(),
  var frameworkErrors: MutableList<Throwable> = mutableListOf(),

  var stubCase: StubCase = StubCase.NONE,
  var workMode: WorkMode = WorkMode.DEFAULT,
  var pageCount: Int = Int.MIN_VALUE,

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

  //Directory
  var requestDirectoryItemId: DirectoryItemIdModel = DirectoryItemIdModel.NONE,
  var requestDirectoryItem: IDirectoryItemModel = DirectoryItemModel.NONE,
  var directoryFilter: DirectoryFilterModel = DirectoryFilterModel.NONE,
  var responseDirectoryItem: IDirectoryItemModel = DirectoryItemModel.NONE,
  var responseDirectoryItems: MutableList<IDirectoryItemModel> = mutableListOf(),

  //Repository Flat
  var flatRepoTest: IFlatRepository = IFlatRepository.NONE,
  var flatRepoProd: IFlatRepository = IFlatRepository.NONE,
  var flatRepo: IFlatRepository = IFlatRepository.NONE,

  //Repository House
  var houseRepoTest: IHouseRepository = IHouseRepository.NONE,
  var houseRepoProd: IHouseRepository = IHouseRepository.NONE,
  var houseRepo: IHouseRepository = IHouseRepository.NONE,

  //Repository Directory
  var directoryRepoTest: IDirectoryRepository = IDirectoryRepository.NONE,
  var directoryRepoProd: IDirectoryRepository = IDirectoryRepository.NONE,
  var directoryRepo: IDirectoryRepository = IDirectoryRepository.NONE,
)
