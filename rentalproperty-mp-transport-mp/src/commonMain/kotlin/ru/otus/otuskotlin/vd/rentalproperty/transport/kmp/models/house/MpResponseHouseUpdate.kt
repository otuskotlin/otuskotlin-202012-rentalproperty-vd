package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.marketplace.transport.kmp.models.common.IMpResponse
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.*

@Serializable
@SerialName("MpResponseHouseUpdate")
data class MpResponseHouseUpdate(
  override val responseId: String? = null,
  override val onRequest: String? = null,
  override val endTime: String? = null,
  override val errors: List<ErrorDto>? = null,
  override val status: ResponseStatusDto? = null,
  override val debug: Debug? = null,
  val house: MpHouseDto? = null,
) : IMpResponse, MpMessage() {

  @Serializable
  data class Debug(
    override val mode: MpWorkModeDto?
  ) : IMpDebug
}