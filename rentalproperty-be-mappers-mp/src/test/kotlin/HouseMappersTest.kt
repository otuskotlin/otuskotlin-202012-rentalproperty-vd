import ru.otus.otuskotlin.marketplace.mappers.openapi.setQuery
import ru.otus.otuskotlin.marketplace.transport.kmp.models.common.IMpRequest
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.MpBeContext
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.HouseMaterialDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.common.realty.HouseTypeDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.MpHouseCreateDto
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.MpRequestHouseCreate
import ru.otus.otuskotlin.vd.rentalproperty.transport.kmp.models.house.MpRequestHouseRead
import kotlin.test.Test
import kotlin.test.assertEquals

internal class HouseMappersTest {

  @Test
  fun requestIdMappingTest() {
    val request: IMpRequest = MpRequestHouseRead(
      houseId = "some-id"
    )
    val context = MpBeContext()

    context.setQuery(request)

    assertEquals("some-id", context.requestHouseId.id)
  }

  @Test
  fun requestCreateMappingTest() {
    val requestHouse: IMpRequest = MpRequestHouseCreate(
      createData = MpHouseCreateDto(
        price = 10_000_000.0,
        material = HouseMaterialDto.BRICK,
        type = HouseTypeDto.SINGLE_HOUSE,
        floors = 2,
        areaPlot = 10.0,
      )
    )
    val context = MpBeContext()

    context.setQuery(requestHouse)

    assertEquals(10_000_000.0, context.requestHouse.price)
    assertEquals(2, context.requestHouse.floors)
  }
}
