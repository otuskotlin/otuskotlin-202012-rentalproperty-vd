package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import java.time.Duration
import java.time.Instant

/**
 * Объявление о сдачи квартиры в аренду
 */
data class AdvertFlatModel(
  val id: AdvertIdModel = AdvertIdModel.NONE,
  val userId: UserIdModel = UserIdModel.NONE,
  val flatId: FlatIdModel = FlatIdModel.NONE,
  val name: String = "",
  val description: String = "",
  /** Стоимость аренды в месяц */
  val price: Double = 0.0,
  /** Дата возможного начала аренды */
  val startDate: Instant = Instant.now(),
  /** Дата возможного окончания аренды */
  val endDate: Instant = Instant.now(),
  /** Минимальный срок аренды */
  val minPeriod: Duration = Duration.ZERO,
  /** if null, then the ad is removed or not displayed */
  val published: Instant? = null,
) {
  companion object {
    val NONE = AdvertFlatModel()
    val STUB = AdvertFlatModel(
      id = AdvertIdModel("test-advert-id"),
      userId = UserIdModel("test-user-id"),
      name = "Продаётся дом",
      description = "Хороший дом",
      price = 1_500_000.0,
      startDate = Instant.now(),
      endDate = Instant.now().plusSeconds(Duration.ofDays(90).toSeconds()),
      minPeriod = Duration.ofDays(1)
    )
  }
}