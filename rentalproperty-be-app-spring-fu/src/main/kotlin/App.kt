import controller.HouseController
import org.springframework.fu.kofu.webApplication
import org.springframework.fu.kofu.webmvc.webMvc

val app = webApplication {
    beans {
        bean<HouseController>()
    }
    webMvc {
        port = if (profiles.contains("test")) 8181 else 8080
        router {
            val houseService = ref<HouseController>()
            POST("/realty/house/list", houseService::list)
            POST("/realty/house/create", houseService::create)
            POST("/realty/house/read", houseService::read)
            POST("/realty/house/update", houseService::update)
            POST("/realty/house/delete", houseService::delete)
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
