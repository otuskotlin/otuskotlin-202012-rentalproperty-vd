package ru.otus.otuskotlin.vd.rentalproperty.be.app.spring.fu

import app
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import ru.otus.otuskotlin.vd.rentalproperty.kmp.common.RestEndpoints
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.ViewFromWindowDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.flat.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class FlatControllerTest {
  private val client = WebTestClient.bindToServer().baseUrl("http://localhost:8181").build()
  private lateinit var context: ConfigurableApplicationContext

  @BeforeAll
  fun beforeAll() {
    context = app.run(profiles = "test")
  }

  @Test
  fun `Flat List`() {
    val res = client
      .post()
      .uri(RestEndpoints.flatList)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(RequestFlatList())
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody<ResponseFlatList>()
      .returnResult()
      .responseBody

    assertEquals(5, res?.flats?.size)
  }

  @Test
  fun `Flat Create`() {
    val res = client
      .post()
      .uri(RestEndpoints.flatCreate)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(
        RequestFlatCreate(createData = FlatCreateDto.STUB)
      )
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody<ResponseFlatCreate>()
      .returnResult()
      .responseBody

    assertEquals("test-flat-id", res?.flat?.id)
    assertEquals(44.4, res?.flat?.area)
    assertEquals(3, res?.flat?.floor)
    assertEquals(ViewFromWindowDto.STUB_PARK, res?.flat?.viewFromWindow)
  }

  @Test
  fun `Flat Read`() {
    val res = client
      .post()
      .uri(RestEndpoints.flatRead)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(
        RequestFlatRead(flatId = "test-flat-id")
      )
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody<ResponseFlatRead>()
      .returnResult()
      .responseBody

    assertEquals("test-flat-id", res?.flat?.id)
    assertEquals(44.4, res?.flat?.area)
    assertEquals(3, res?.flat?.floor)
    assertEquals(ViewFromWindowDto.STUB_PARK, res?.flat?.viewFromWindow)
  }

  @Test
  fun `Flat Update`() {
    val res = client
      .post()
      .uri(RestEndpoints.flatUpdate)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(
        RequestFlatUpdate(updateData = FlatUpdateDto.STUB)
      )
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody<ResponseFlatUpdate>()
      .returnResult()
      .responseBody

    assertEquals("test-flat-id", res?.flat?.id)
    assertEquals(44.4, res?.flat?.area)
    assertEquals(3, res?.flat?.floor)
    assertEquals(ViewFromWindowDto.STUB_PARK, res?.flat?.viewFromWindow)
  }

  @Test
  fun `Flat Delete`() {
    val res = client
      .post()
      .uri(RestEndpoints.flatDelete)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(
        RequestFlatDelete(flatId = "test-flat-id")
      )
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody<ResponseFlatDelete>()
      .returnResult()
      .responseBody

    assertEquals("test-flat-id", res?.flat?.id)
    assertTrue(res?.deleted!!)
  }

  @AfterAll
  fun afterAll() {
    context.close()
  }

}
