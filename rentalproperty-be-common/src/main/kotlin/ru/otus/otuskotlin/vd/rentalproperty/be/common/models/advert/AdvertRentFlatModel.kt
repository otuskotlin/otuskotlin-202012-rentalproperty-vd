package ru.otus.otuskotlin.vd.rentalproperty.be.common.models.advert

import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.person.UserIdModel
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.realty.FlatIdModel
import java.time.Instant

/**
 * Объявление о сдачи квартиры в аренду
 */
data class AdvertRentFlatModel(
  val id: AdvertIdModel = AdvertIdModel.NONE,
  val userId: UserIdModel = UserIdModel.NONE,
  var flatId: FlatIdModel = FlatIdModel.NONE,
  var name: String = "",
  val description: String = "",
  val price: Double = 0.0,
  /**
   * if null, then the ad is removed or not displayed
   */
  var published: Instant? = null,
) {
  companion object {
    val NONE = AdvertRentFlatModel()
  }
}