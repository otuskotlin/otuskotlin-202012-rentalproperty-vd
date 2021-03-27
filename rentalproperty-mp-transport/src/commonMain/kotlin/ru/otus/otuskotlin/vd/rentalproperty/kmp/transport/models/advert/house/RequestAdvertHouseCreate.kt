package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.advert.house

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IDebug
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.WorkModeDto

@Serializable
@SerialName("RequestAdvertHouseCreate")
data class RequestAdvertHouseCreate(
  override val requestId: String? = null,
  override val onResponse: String? = null,
  override val startTime: String? = null,
  override val debug: RequestAdvertHouseCreate.Debug? = null,
  val createData: AdvertHouseCreateDto? = null,
) : IRequest, Message() {

  @Serializable
  data class Debug(
    override val mode: WorkModeDto?,
    val stubCase: RequestAdvertHouseCreate.StubCase? = null
  ) : IDebug

  @Serializable
  enum class StubCase {
    NONE,
    SUCCESS
  }
}
