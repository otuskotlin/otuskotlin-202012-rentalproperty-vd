package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.marketplace.transport.kmp.models.common.IMpRequest
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.IMpDebug
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.MpMessage
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.MpWorkModeDto

@Serializable
@SerialName("MpRequestHouseDelete")
data class MpRequestHouseDelete(
  override val requestId: String? = null,
  override val onResponse: String? = null,
  override val startTime: String? = null,
  override val debug: Debug? = null,
  val houseId: String? = null,
) : IMpRequest, MpMessage() {

  @Serializable
  data class Debug(
    override val mode: MpWorkModeDto?
  ) : IMpDebug
}
