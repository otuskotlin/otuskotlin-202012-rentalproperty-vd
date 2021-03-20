import com.example.jsonConfig
import com.example.module
import io.ktor.http.*
import io.ktor.server.testing.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.advert.house.RequestAdvertRentHouseRead
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.advert.house.ResponseAdvertRentHouseRead
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ResponseStatusDto
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class RouteAdvertHouseIT {

  @Test
  fun testGet() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Post, "/adverts/houses/get") {
        val body = RequestAdvertRentHouseRead(
          requestId = "321",
          advertId = "test-id",
        )

        val format = jsonConfig

        val bodyString = format.encodeToString(Message.serializer(), body)
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(
          ContentType.Application.Json.withCharset(Charsets.UTF_8),
          response.contentType()
        )
        val jsonString = response.content ?: fail("Null response json")
        println(jsonString)

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString)
            as? ResponseAdvertRentHouseRead)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.SUCCESS, res.status)
        assertEquals("321", res.onRequest)
        assertEquals("Продаётся квартира", res.advert?.name)
      }
    }
  }
}
