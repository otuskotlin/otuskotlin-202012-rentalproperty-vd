import com.example.jsonConfig
import com.example.module
import io.ktor.http.*
import io.ktor.server.testing.*
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.Message
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.ResponseStatusDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.RequestHouseRead
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.ResponseHouseRead
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class ApplicationTest {
  @Test
  fun testRoot() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Get, "/").apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals("HELLO WORLD!", response.content)
      }
    }
  }

  @Test
  fun testGet() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Post, "/realty/houses/get") {
        val body = RequestHouseRead(
          requestId = "321",
          houseId = "12345",
        )

        val format = jsonConfig

        val bodyString = format.encodeToString(Message.serializer(), body)
        setBody(bodyString)
        addHeader("Content-Type", "application/json")
      }.apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
        val jsonString = response.content ?: fail("Null response json")
        println(jsonString)

        val res = (jsonConfig.decodeFromString(Message.serializer(), jsonString) as? ResponseHouseRead)
          ?: fail("Incorrect response format")

        assertEquals(ResponseStatusDto.SUCCESS, res.status)
        assertEquals("321", res.onRequest)
        assertEquals("Moscow", res.house?.address)
      }
    }
  }
}
