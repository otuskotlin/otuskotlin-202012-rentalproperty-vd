package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IDebug
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.WorkModeDto

@Serializable
@SerialName("RequestHouseDelete")
data class RequestHouseDelete(
  override val requestId: String? = null,
  override val onResponse: String? = null,
  override val startTime: String? = null,
  override val debug: RequestHouseDelete.Debug? = null,
  val houseId: String? = null,
) : IRequest, Message() {

  @Serializable
  data class Debug(
    override val mode: WorkModeDto? = null,
    val stubCase: RequestHouseDelete.StubCase? = null
  ) : IDebug

  @Serializable
  enum class StubCase {
    NONE,
    SUCCESS
  }
}
