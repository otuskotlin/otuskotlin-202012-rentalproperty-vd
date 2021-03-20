package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.realty.house

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.marketplace.transport.kmp.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.IDebug
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.WorkModeDto

@Serializable
@SerialName("RequestHouseUpdate")
data class RequestHouseUpdate(
  override val requestId: String? = null,
  override val onResponse: String? = null,
  override val startTime: String? = null,
  override val debug: Debug? = null,
  val updateData: HouseUpdateDto? = null,
) : IRequest, Message() {

  @Serializable
  data class Debug(
    override val mode: WorkModeDto?
  ) : IDebug
}