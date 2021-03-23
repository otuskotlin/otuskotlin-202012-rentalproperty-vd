import com.example.module
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationIT {

  @Test
  fun testRoot() {
    withTestApplication({ module(testing = true) }) {
      handleRequest(HttpMethod.Get, "/").apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals("HELLO WORLD!", response.content)
      }
    }
  }
}
