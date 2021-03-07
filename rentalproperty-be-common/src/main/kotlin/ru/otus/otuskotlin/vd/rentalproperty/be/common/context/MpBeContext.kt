package ru.otus.otuskotlin.vd.rentalproperty.be.common.context

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpHouseFilterModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpHouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.MpHouseModel

data class MpBeContext(
    var requestHouseId: MpHouseIdModel = MpHouseIdModel.NONE,
    var requestHouse: MpHouseModel = MpHouseModel.NONE,

    var houseFilter: MpHouseFilterModel = MpHouseFilterModel.NONE,

    var responseHouse: MpHouseModel = MpHouseModel.NONE,
    var responseHouses: MutableList<MpHouseModel> = mutableListOf(),
)
