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
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.HouseTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class HouseControllerTest {
  private val client = WebTestClient
    .bindToServer()
    .baseUrl("http://localhost:8181")
    .build()
  private lateinit var context: ConfigurableApplicationContext

  @BeforeAll
  fun beforeAll() {
    context = app.run(profiles = "test")
  }

  @Test
  fun `House List`() {
    val res = client
      .post()
      .uri(RestEndpoints.houseList)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(
        RequestHouseList(
          debug = RequestHouseList.Debug(
            stubCase = RequestHouseList.StubCase.SUCCESS
          ),
        )
      )
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody<ResponseHouseList>()
      .returnResult()
      .responseBody

    assertEquals(1, res?.houses?.size)
  }

  @Test
  fun `House Create`() {
    val res = client
      .post()
      .uri(RestEndpoints.houseCreate)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(
        RequestHouseCreate(
          createData = HouseCreateDto.STUB_SINGLE_HOUSE,
          debug = RequestHouseCreate.Debug(
            stubCase = RequestHouseCreate.StubCase.SUCCESS
          ),
        )
      )
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody<ResponseHouseCreate>()
      .returnResult()
      .responseBody

    assertEquals("test-house-id", res?.house?.id)
    assertEquals("test-address", res?.house?.address)
    assertEquals(
      HouseTypeDto.STUB_SINGLE_HOUSE, res?.house?.type
    )
    assertEquals(2, res?.house?.floors)
  }

  @Test
  fun `House Read`() {
    val res = client
      .post()
      .uri(RestEndpoints.houseRead)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(
        RequestHouseRead(
          houseId = "test-house-id",
          debug = RequestHouseRead.Debug(
            stubCase = RequestHouseRead.StubCase.SUCCESS
          ),
        )
      )
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody<ResponseHouseRead>()
      .returnResult()
      .responseBody

    assertEquals("test-house-id", res?.house?.id)
    assertEquals("test-address", res?.house?.address)
    assertEquals(HouseTypeDto.STUB_SINGLE_HOUSE, res?.house?.type)
    assertEquals(2, res?.house?.floors)
  }

  @Test
  fun `House Update`() {
    val res = client
      .post()
      .uri(RestEndpoints.houseUpdate)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(
        RequestHouseUpdate(
          updateData = HouseUpdateDto.STUB_MULTI_APARTMENT,
          debug = RequestHouseUpdate.Debug(
            stubCase = RequestHouseUpdate.StubCase.SUCCESS
          ),
        )
      )
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody<ResponseHouseUpdate>()
      .returnResult()
      .responseBody

    assertEquals("test-house-id", res?.house?.id)
    assertEquals("test-address", res?.house?.address)
    assertEquals(HouseTypeDto.STUB_MULTI_APARTMENT, res?.house?.type)
    assertEquals(5, res?.house?.floors)
  }

  @Test
  fun `House Delete`() {
    val res = client
      .post()
      .uri(RestEndpoints.houseDelete)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(
        RequestHouseDelete(
          houseId = "test-house-id",
          debug = RequestHouseDelete.Debug(
            stubCase = RequestHouseDelete.StubCase.SUCCESS
          ),
        )
      )
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody<ResponseHouseDelete>()
      .returnResult()
      .responseBody

    assertEquals("test-house-id", res?.house?.id)
    assertTrue(res?.deleted!!)
  }

  @AfterAll
  fun afterAll() {
    context.close()
  }

}
