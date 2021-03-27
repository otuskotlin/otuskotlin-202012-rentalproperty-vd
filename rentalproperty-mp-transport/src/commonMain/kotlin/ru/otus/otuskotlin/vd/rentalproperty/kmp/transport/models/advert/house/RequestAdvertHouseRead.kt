package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IDebug
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.WorkModeDto

@Serializable
@SerialName("RequestAdvertHouseRead")
data class RequestAdvertHouseRead(
  override val requestId: String? = null,
  override val onResponse: String? = null,
  override val startTime: String? = null,
  override val debug: RequestAdvertHouseRead.Debug? = null,
  val advertId: String? = null,
) : IRequest, Message() {

  @Serializable
  data class Debug(
    override val mode: WorkModeDto?,
    val stubCase: RequestAdvertHouseRead.StubCase? = null
  ) : IDebug

  @Serializable
  enum class StubCase {
    NONE,
    SUCCESS
  }
}
