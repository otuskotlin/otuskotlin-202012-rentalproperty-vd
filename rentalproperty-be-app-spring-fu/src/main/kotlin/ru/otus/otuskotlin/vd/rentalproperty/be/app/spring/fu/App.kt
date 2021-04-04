import org.springframework.fu.kofu.webApplication
import org.springframework.fu.kofu.webmvc.webMvc
import ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.fu.controller.AdvertHouseController
import ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.fu.controller.HouseController
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.AdvertHouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.HouseCrud
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints

val app = webApplication {
  beans {
    bean<HouseCrud>()
    bean<HouseController>()

    bean<AdvertHouseCrud>()
    bean<AdvertHouseController>()
  }
  webMvc {
    port = if (profiles.contains("test")) 8181 else 8080
    router {
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
