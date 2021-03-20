package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.HouseIdModel
import java.time.Instant

/**
 * Объявление о сдачи дома в аренду
 */
data class AdvertRentHouseModel(
  val id: AdvertIdModel = AdvertIdModel.NONE,
  val userId: UserIdModel = UserIdModel.NONE,
  val houseId: HouseIdModel = HouseIdModel.NONE,
  val name: String = "",
  val description: String = "",
  val price: Double = 0.0,
  /**
   * if null, then the ad is removed or not displayed
   */
  val published: Instant? = null,
) {
  companion object {
    val NONE = AdvertRentHouseModel()
  }
}