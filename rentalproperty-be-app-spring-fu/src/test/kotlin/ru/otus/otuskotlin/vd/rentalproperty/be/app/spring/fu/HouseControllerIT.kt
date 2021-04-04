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
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.HouseMaterialDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.directory.HouseTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.realty.house.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class HouseControllerIT {
  private val client = WebTestClient.bindToServer().baseUrl("http://localhost:8181").build()
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
      .bodyValue(RequestHouseList())
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody<ResponseHouseList>()
      .returnResult()
      .responseBody

    assertEquals(5, res?.houses?.size)
  }

  @Test
  fun `House Create`() {
    val res = client
      .post()
      .uri(RestEndpoints.houseCreate)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(
        RequestHouseCreate(
          createData = HouseCreateDto(
            area = 100.0,
            address = "Novosibirsk",
            material = HouseMaterialDto(
              "id",
              "BRICK"
            ),
            type = HouseTypeDto(
              "id",
              "SINGLE_HOUSE"
            ),
            floors = 1,
            areaPlot = 5.0,
          )
        )
      )
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody<ResponseHouseCreate>()
      .returnResult()
      .responseBody

    assertEquals("house123", res?.house?.id)
    assertEquals("Novosibirsk", res?.house?.address)
    assertEquals(
      HouseTypeDto(
        "id",
        "SINGLE_HOUSE"
      ), res?.house?.type
    )
    assertEquals(1, res?.house?.floors)
  }

  @Test
  fun `House Read`() {
    val res = client
      .post()
      .uri(RestEndpoints.houseRead)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(
        RequestHouseRead(
          houseId = "house123",
        )
      )
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody<ResponseHouseRead>()
      .returnResult()
      .responseBody

    assertEquals("house123", res?.house?.id)
    assertEquals("Moscow", res?.house?.address)
    assertEquals(
      HouseTypeDto(
        "id",
        "SINGLE_HOUSE"
      ), res?.house?.type
    )
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
          updateData = HouseUpdateDto(
            id = "house321",
            area = 330.0,
            address = "Petersburg",
            material = HouseMaterialDto(
              "id",
              "BRICK"
            ),
            type = HouseTypeDto(
              "id",
              "SINGLE_HOUSE"
            ),
            floors = 3,
            areaPlot = 13.5
          )
        )
      )
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody<ResponseHouseDelete>()
      .returnResult()
      .responseBody

    assertEquals("house321", res?.house?.id)
    assertEquals("Petersburg", res?.house?.address)
    assertEquals(
      HouseTypeDto(
        "id",
        "SINGLE_HOUSE"
      ), res?.house?.type
    )
    assertEquals(3, res?.house?.floors)
  }

  @Test
  fun `House Delete`() {
    val res = client
      .post()
      .uri(RestEndpoints.houseDelete)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(
        RequestHouseDelete(
          houseId = "house123",
        )
      )
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody<ResponseHouseDelete>()
      .returnResult()
      .responseBody

    assertEquals("house123", res?.house?.id)
    assertTrue(res?.deleted!!)
  }

  @AfterAll
  fun afterAll() {
    context.close()
  }

}
