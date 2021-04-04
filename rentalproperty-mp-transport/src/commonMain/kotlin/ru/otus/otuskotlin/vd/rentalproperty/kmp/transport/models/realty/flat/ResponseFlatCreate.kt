package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.*

@Serializable
@SerialName("ResponseFlatCreate")
data class ResponseFlatCreate(
  override val responseId: String? = null,
  override val onRequest: String? = null,
  override val endTime: String? = null,
  override val errors: List<ErrorDto>? = null,
  override val status: ResponseStatusDto? = null,
  override val debug: Debug? = null,
  val house: FlatDto? = null,
) : IResponse, Message() {

  @Serializable
  data class Debug(
    override val mode: WorkModeDto?
  ) : IDebug
}
