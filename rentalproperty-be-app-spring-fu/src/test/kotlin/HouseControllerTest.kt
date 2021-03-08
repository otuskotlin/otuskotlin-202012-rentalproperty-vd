import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.HouseMaterialDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.HouseTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MarketplaceHouseIT {
    private val client = WebTestClient.bindToServer().baseUrl("http://localhost:8181").build()
    private lateinit var context: ConfigurableApplicationContext
    private val REALTY_ENDPOINT: String = "/realty"

    @BeforeAll
    fun beforeAll() {
        context = app.run(profiles = "test")
    }

    @Test
    fun `House List`() {
        val res = client
            .post()
            .uri("$REALTY_ENDPOINT/house/list")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .bodyValue(MpRequestHouseList())
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<MpResponseHouseList>()
            .returnResult()
            .responseBody

        assertEquals(5, res?.houses?.size)
    }

    @Test
    fun `House Create`() {
        val res = client
            .post()
            .uri("$REALTY_ENDPOINT/house/create")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .bodyValue(
                MpRequestHouseCreate(
                    createData = MpHouseCreateDto(
                        price = 5_000_000.0,
                        area = 100.0,
                        address = "Novosibirsk",
                        material = HouseMaterialDto.BRICK,
                        type = HouseTypeDto.SINGLE_HOUSE,
                        floors = 1,
                        areaPlot = 5.0,
                    )
                )
            )
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<MpResponseHouseCreate>()
            .returnResult()
            .responseBody

        assertEquals("house123", res?.house?.id)
        assertEquals("Novosibirsk", res?.house?.address)
        assertEquals(HouseTypeDto.SINGLE_HOUSE, res?.house?.type)
        assertEquals(1, res?.house?.floors)
    }

    @Test
    fun `House Read`() {
        val res = client
            .post()
            .uri("$REALTY_ENDPOINT/house/read")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .bodyValue(
                MpRequestHouseRead(
                    houseId = "house123",
                )
            )
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<MpResponseHouseRead>()
            .returnResult()
            .responseBody

        assertEquals("house123", res?.house?.id)
        assertEquals("Moscow", res?.house?.address)
        assertEquals(HouseTypeDto.SINGLE_HOUSE, res?.house?.type)
        assertEquals(2, res?.house?.floors)
    }

    @Test
    fun `House Update`() {
        val res = client
            .post()
            .uri("$REALTY_ENDPOINT/house/update")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .bodyValue(
                MpRequestHouseUpdate(
                    updateData = MpHouseUpdateDto(
                        id = "house321",
                        price = 13_000_000.0,
                        area = 330.0,
                        address = "Petersburg",
                        material = HouseMaterialDto.BRICK,
                        type = HouseTypeDto.SINGLE_HOUSE,
                        floors = 3,
                        areaPlot = 13.5
                    )
                )
            )
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<MpResponseHouseDelete>()
            .returnResult()
            .responseBody

        assertEquals("house321", res?.house?.id)
        assertEquals("Petersburg", res?.house?.address)
        assertEquals(HouseTypeDto.SINGLE_HOUSE, res?.house?.type)
        assertEquals(3, res?.house?.floors)
    }

    @Test
    fun `House Delete`() {
        val res = client
            .post()
            .uri("$REALTY_ENDPOINT/house/delete")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .bodyValue(
                MpRequestHouseDelete(
                    houseId = "d-6543",
                )
            )
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<MpResponseHouseDelete>()
            .returnResult()
            .responseBody

        assertEquals("d-6543", res?.house?.id)
        assertTrue(res?.deleted!!)
    }

    @AfterAll
    fun afterAll() {
        context.close()
    }

}
