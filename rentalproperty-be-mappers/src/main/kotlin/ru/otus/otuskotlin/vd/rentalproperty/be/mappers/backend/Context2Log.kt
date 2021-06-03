package ru.otus.otuskotlin.vd.rentalproperty.be.mappers.backend

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.logs.CommonLogModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.logs.LogModel
import java.time.Instant
import java.util.*

fun BeContext.toLog(logId: String) = CommonLogModel(
    messageId = UUID.randomUUID().toString(),
    messageTime = Instant.now().toString(),
    source = "ok-rentalproperty",
    logId = logId,
    rentalproperty = LogModel(
        requestFlatId = requestFlatId.takeIf { it != FlatIdModel.NONE }?.asString(),
        requestHouseId = requestHouseId.takeIf { it != HouseIdModel.NONE }?.asString(),
        requestFlat = requestFlat.takeIf { it != FlatModel.NONE }?.toTransport(),
        requestHouse = responseHouse.takeIf { it != HouseModel.NONE }?.toTransport(),
        responseFlat = responseFlat.takeIf { it != FlatModel.NONE }?.toTransport(),
        responseHouse = responseHouse.takeIf { it != HouseModel.NONE }?.toTransport(),
        responseFlats = responseFlats.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
        responseHouses = responseHouses.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    ),
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
)
