package ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IDebug
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.IRequest
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.WorkModeDto

@Serializable
@SerialName("RequestFlatDelete")
data class RequestFlatDelete(
  override val requestId: String? = null,
  override val onResponse: String? = null,
  override val startTime: String? = null,
  override val debug: Debug? = null,
  val flatId: String? = null,
) : IRequest, Message() {

  @Serializable
  data class Debug(
    override val mode: WorkModeDto? = null,
    val stubCase: StubCase? = null
  ) : IDebug

  @Serializable
  enum class StubCase {
    NONE,
    SUCCESS
  }
}
