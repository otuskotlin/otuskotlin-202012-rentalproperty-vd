import org.springframework.fu.kofu.webApplication
import org.springframework.fu.kofu.webmvc.webMvc
import ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.fu.controller.AdvertFlatController
import ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.fu.controller.AdvertHouseController
import ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.fu.controller.FlatController
import ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.fu.controller.HouseController
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.AdvertFlatCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.AdvertHouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.FlatCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.HouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints

val app = webApplication {
  beans {
    bean<FlatCrud>()
    bean<FlatController>()

    bean<AdvertFlatCrud>()
    bean<AdvertFlatController>()

    bean<HouseCrud>()
    bean<HouseController>()

    bean<AdvertHouseCrud>()
    bean<AdvertHouseController>()
  }
  webMvc {
    port = if (profiles.contains("test")) 8181 else 8080
    router {
      val flatService = ref<FlatController>()
      POST(RestEndpoints.flatList, flatService::list)
      POST(RestEndpoints.flatCreate, flatService::create)
      POST(RestEndpoints.flatRead, flatService::read)
      POST(RestEndpoints.flatUpdate, flatService::update)
      POST(RestEndpoints.flatDelete, flatService::delete)

      val advertFlatService = ref<AdvertFlatController>()
      POST(RestEndpoints.advertFlatList, advertFlatService::list)
      POST(RestEndpoints.advertFlatCreate, advertFlatService::create)
      POST(RestEndpoints.advertFlatRead, advertFlatService::read)
      POST(RestEndpoints.advertFlatUpdate, advertFlatService::update)
      POST(RestEndpoints.advertFlatDelete, advertFlatService::delete)

      val houseService = ref<HouseController>()
      POST(RestEndpoints.houseList, houseService::list)
      POST(RestEndpoints.houseCreate, houseService::create)
      POST(RestEndpoints.houseRead, houseService::read)
      POST(RestEndpoints.houseUpdate, houseService::update)
      POST(RestEndpoints.houseDelete, houseService::delete)

      val advertHouseService = ref<AdvertHouseController>()
      POST(RestEndpoints.advertHouseList, advertHouseService::list)
      POST(RestEndpoints.advertHouseCreate, advertHouseService::create)
      POST(RestEndpoints.advertHouseRead, advertHouseService::read)
      POST(RestEndpoints.advertHouseUpdate, advertHouseService::update)
      POST(RestEndpoints.advertHouseDelete, advertHouseService::delete)
    }
    converters {
      string()
      kotlinSerialization()
    }
  }
}

fun main() {
  app.run()
}
