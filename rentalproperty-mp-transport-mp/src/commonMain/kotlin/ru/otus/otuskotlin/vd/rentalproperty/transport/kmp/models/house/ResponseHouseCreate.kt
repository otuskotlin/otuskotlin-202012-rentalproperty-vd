package ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.marketplace.transport.kmp.models.common.IResponse
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.*

@Serializable
@SerialName("ResponseHouseCreate")
data class ResponseHouseCreate(
  override val responseId: String? = null,
  override val onRequest: String? = null,
  override val endTime: String? = null,
  override val errors: List<ErrorDto>? = null,
  override val status: ResponseStatusDto? = null,
  override val debug: Debug? = null,
  val house: HouseDto? = null,
) : IResponse, Message() {

  @Serializable
  data class Debug(
    override val mode: WorkModeDto?
  ) : IDebug
}
